# 微服务技术栈

---

### 初识微服务
![微服务架构]()
- 单体架构特点
    - 简单方便，高度耦合，扩展性差，适合小型项目。例如：学生管理系统
- 分布式架构特点
    - 松耦合，扩展性好，但架构复杂，难度大。适合大型互联网项目，例如：京东、淘宝
- 微服务：一种良好的分布式架构方案
    - 优点：拆分粒度更小、服务更独立、耦合度更低
    - 缺点：架构非常复杂，运维、监控、部署难度提高

### Dubbo、SpringCloud、SpringCloudAlibaba
- 微服务技术对比
![微服务技术]()
- SpringCloud
![springcloud]()

### Eureka
- EurekaServer：服务端，注册中心
    - 记录服务信息
    - 心跳监控
- EurekaClient：客户端
    - Provider：服务提供者，例如案例中的 user-server
        - 注册自己的信息到EurekaServer
        - 每隔30秒向EurekaServer发送心跳
    - Consumer：服务消费者，例如案例中的 order-server
        - 根据服务名称从EurekaServer拉取服务列表
        - 基于服务列表做负载均衡，选中一个微服务发起远程调用 
- 搭建EurekaServer
    - 引入eureka-server依赖
    - 添加@EnableEurekaServer注解
    - 在application.yml中配置eureka地址
- 服务注册
    - 引入eureka-client依赖
    - 在application.yml中配置eureka地址
- 服务发现
    - 引入eureka-client依赖
    - 在application.yml中配置eureka地址
    - 给RestTemplate添加@LoadBalanced注解
    - 用服务提供者的服务名称远程调用    

### Ribbon
- 负载均衡拦截器
![ribbon-负载均衡原理]()
- 负载均衡策略
![ribbon-负载均衡策略]()
- 饥饿加载
![ribbon-饥饿加载]()
- Ribbon负载均衡规则
    - 规则接口是IRule
    - 默认实现是ZoneAvoidanceRule，根据zone选择服务列表，然后轮询
- 负载均衡自定义方式
    - 代码方式：配置灵活，但修改时需要重新打包发布
    - 配置方式：直观，方便，无需重新打包发布，但是无法做全局配置
- 饥饿加载
    - 开启饥饿加载
    - 指定饥饿加载的微服务名称

### Nacos
- Nacos服务多级存储模型
![nacos-服务分级存储模型]()
    - Nacos服务分级存储模型
        - 一级是服务，例如userservice
        - 二级是集群，例如杭州或上海
        - 三级是实例，例如杭州机房的某台部署了userservice的服务器
    - 如何设置实例的集群属性
        - 修改application.yml文件，添加spring.cloud.nacos.discovery.cluster-name属性即可
- NacosRule负载均衡策略
    - 优先选择同集群服务实例列表
    - 本地集群找不到提供者，才去其他集群寻找，并且会报警告
    - 确定了可用实例列表后，再采用随机负载均衡挑选实例
- Nacos服务实例的权重设置
    - Nacos控制台可以设置实例的权重值，0~1之间
    - 同集群内的多个实例，权重越高被访问的频率越高
    - 权重设置为0则完全不会被访问
- Nacos环境隔离
    - namespace用来做环境隔离
    - 每个namespace都有唯一id
    - 不同namespace下的服务不可见
- Nacos与Eureka的共同点
    - 都支持服务注册和服务拉取
    - 都支持服务提供者心跳方式做健康检测
- Nacos与Eureka的区别
    - Nacos支持服务端主动检测提供者状态：临时实例采用心跳模式，非临时实例采用自动检测模式
    - 临时实例心跳不正常会被剔除，非临时实例则不会被剔除
    - Nacos支持服务列表变更的消息推送模式，服务列表更新更及时
    - Nacos集群默认采用AP方式，当集群中存在非临时实例时，采用CP模式；Eureka采用AP方式

### Feign
- RestTemplate方式远程调用存在的问题
![resttemplate]()
- Feign的介绍
![feign]()
- Feign自定义配置
![feign-自定义配置]()
    - Feign的日志配置
        - 方式一是配置文件，feign.client.config.xxx.loggerLevel
            - 如果xxx是default则代表全局
            - 如果xxx是服务名称，例如userservice则代表某服务
        - 方式二是java代码配置Logger.Level这个Bean
            - 如果在@EnableFeignClients注解声明则代表全局
            - 如果在@FeignClient注解中声明则代表某服务

### Gateway
- 网关的技术实现
![gateway]()
- 网关的作用
    - 对用户请求做身份验证、权限校验
    - 将用户请求路由到微服务，并实现负载均衡
    - 对用户请求做限流
- Gateway路由断言工厂
![route predicate factory]()
    - PredicateFactory的作用
        - 读取用户定义的断言条件，对请求做出判断
- Gateway路由的过滤器配置
    - 过滤器的作用
        - 对路由的请求或影响应加工处理，比如添加请求头
        - 配置在路由下的过滤器只对当前路由的请求生效
    - defaultFilters的作用
        - 对所有路由都生效的过滤器
- Gateway全局过滤器
    - 全局过滤器的作用
        - 对所有路由都生效的过滤器，并且可以自定义处理逻辑
    - 实现全局过滤器的步骤
        - 实现GlobalFilter接口
        - 添加@Order注解或实现Ordered接口
        - 编写处理逻辑 
- Gateway过滤器链执行顺序
    - 路由过滤器、defaultFilter、全局过滤器的执行顺序
        - order值越小，优先级越高
        - 当order值一样时，顺序是defaultFilter最先，然后是局部的路由过滤器，最后是全局过滤器      
- Gateway网关的CORS跨域配置
![cors跨域问题处理]()
    - CORS跨域要配置的参数
        - 允许哪些域名跨域
        - 允许哪些请求头
        - 允许哪些请求方式
        - 是否允许使用cookie
        - 有效期是多久

##### ___以上微服务组件技术可参考 Cloud-Demo 项目___

---

### Docker
- Docker是一个快速交付应用、运行应用的技术
    - 可以将程序及其依赖、运行环境一起打包为一个镜像，可以迁移到任意Linux操作系统
    - 运行时利用沙箱机制形成隔离容器，各个引用互不干扰
    - 启动、移除都可以通过一行命令完成，快捷方便
- Docker和虚拟机的差异
    - docker是一个系统进程；虚拟机是在操作系统中的操作系统
    - docker体积小、启动速度快、性能好；虚拟机体积大、启动速度慢、性能一般
- Docker架构
    - 镜像
        - 将应用程序及其依赖、环境、配置打包在一起
    - 容器
        - 镜像运行起来就是容器，一个镜像可以运行多个容器
    - Docker结构
        - 服务端：接受命令或远程请求，操作镜像或容器
        - 客户端：发送命令或者请求到Docker服务端
    - DockerHub
        - 一个镜像托管的服务器，类似的还有阿里云镜像服务，统称为Docker Registry
- 镜像命令
    - docker images：查看所有镜像
    - docker rmi： 删除指定镜像
    - docker pull：拉取指定镜像
    - docker push：将本地镜像上传到镜像仓库
    - docker save：保存镜像为tar文件
    - docker load：读取tar文件生成镜像
- 容器命令
    - docker run：运行镜像创建容器
    - docker rm：删除指定容器
    - docker ps：查看所有运行的容器及状态
    - docker pause：暂停容器（不删进程）
    - docker unpause：运行容器（和pause配套使用）
    - docker stop：停止容器（删进程）
    - docker start：启动容器（和stop配套使用）
    - docker exec：进入容器执行命令
    - docker logs：查看容器日志
- 数据卷命令
    - 数据卷的作用
        - 将容器与数据分离，解耦合，方便操作容器内数据，保证数据安全
    - 数据卷操作
        - docker volume create：创建数据卷
        - docker volume ls：查看所有数据卷
        - docker volume inspect：查看数据卷详情信息
        - docker volume rm：删除指定数据卷
        - docker volume prune：删除未使用的数据卷
    - docker run的命令中通过 -v 参数挂载文件或目录到容器中
        - -v volume名称:容器内目录
        - -v 宿主机文件:容器内文件
        - -v 宿主机目录:容器内目录
    - 数据卷挂载与目录直接挂载的区别
        - 数据卷挂载耦合度低，由docker来管理目录，但是目录较深，不好找
        - 目录挂载耦合度高，需要我们自己管理目录，不过目录容易寻找查看
- Dockerfile
![dockerfile]()
    - docker build：使用dockerfile文件构建一个新镜像
    - Dockerfile的本质是一个文件，通过指令描述镜像的构建过程
    - Dockerfile的第一行必须是From，从一个基础镜像来构建
    - 基础镜像可以是基本操作系统，如Ubuntu。也可以是其他人制作好的镜像，例如：java:8-alpine
    
- DockerCompose
![dockercompose]()
    - docker-compose up：运行dockercompose文件创建并执行容器
    - docker-compose stop：停止执行的容器
    - docker-compose down：停止并删除执行的容器
    - docker-copmose restart：重启服务
    - docker-compose logs：查看日志
    - DockerCompose的作用：帮助我们快速部署分布式运用，无需一个个微服务去构建镜像和部署
- 镜像仓库
![docker registry]()
    - 推送本地镜像到仓库前必须重命名（docker tag）镜像，以镜像仓库地址为前缀
    - 镜像仓库推送前需要把仓库地址配置到docker服务的daemon.json文件中，被docker信任
    - 推送使用docker push命令
    - 拉取使用docker pull命令

---

### MQ
![mq]()
- 同步通讯
    - 同步调用的优点
        - 时效性较强，可以立即得到结果
    - 同步调用的问题
        - 耦合度高
        - 性能和吞吐能力下降
        - 有额外的资源消耗
        - 有级联失败的问题
- 异步通讯
    - 异步通信的优点
        - 耦合度低
        - 吞吐量提升
        - 故障隔离
        - 流量削峰
    - 异步通信的缺点
        - 依赖于Broker的可靠性、安全性、吞吐能力
        - 架构复杂了，业务没有明显的流程线，不好追踪管理
- RabbitMQ
    - RabbitMQ相关知识请转到：[rabbitmq](https://github.com/JackyST0/Java-Technology-Stack/blob/master/RabbitMQ.md)

##### ___以上MQ中间件技术可参考 Mq-Demo 项目___

---

### ES
![elk]()
- 倒排索引
    - 文档和词条
        - 每一条数据就是一个文档
        - 对文档中的内容分词，得到的词语就是词条
    - 正向索引
        - 基于文档id创建索引。查询词条时必须先找到文档，而后判断是否包含词条
    - 倒排索引
        - 对文档内容分词，对词条创建索引，并记录词条所在文档的信息。查询时先根据词条查询到文档id，而后获取到文档
- es与mysql的概念对比
    - 文档：一条数据就是一个文档，es中是Json格式
    - 字段：Json文档中的字段
    - 索引：同类型文档的集合
    - 映射：索引中文档的约束，比如字段名称、类型
    - elasticsearch与数据库的关系
        - 数据库负责事务类型操作
        - elasticsearch负责海量数据的搜索、分析、计算
- ik分词器
    - 分词器的作用
        - 创建倒排索引时对文档分词
        - 用户搜索时，对输入的内容分词
    - IK分词器的模式
        - ik_smart：智能切分，粗粒度
        - ik_max_word：最细切分，细粒度
    - IK分词器拓展词条、停用词条
        - 利用config目录的IkAnalyzer.xml文件添加拓展词典和停用词典
        - 在词典中添加拓展词条或者停用词条
- 索引库操作
    - mapping常见属性
        - type：数据类型
        - index：是否索引
        - analyzer：分词器
        - properties：子字段
    - 常见type
        - 字符串：text、keyword
        - 数字：long、integer、short、byte、double、float
        - 布尔：boolean
        - 日期：data
        - 对象：object
    - 操作索引库
        - 创建索引库：PUT /索引库名
        - 查询索引库：GET /索引库名
        - 删除索引库：DELETE /索引库名
        - 添加字段：PUT /索引库名/_mapping
    - 操作文档
        - 创建文档：POST /索引库名/_doc/文档id {json文档}
        - 查询文档：GET /索引库名/_doc/文档id
        - 删除文档：DELETE /索引库名/_doc/文档id
        - 修改文档
            - 全量修改：PUT /索引库名/_doc/文档id {json文档}
            - 增量修改：POST /索引库名/_update/文档id {"doc":{字段}}
- RestClient
![restclient]()
    - RestClient索引库操作的基本步骤
        - 初始化RestHighLevelClient
        - 创建XxxIndexRequest。Xxx是Create、Get、Delete
        - 准备DSL（CREATE时需要）
        - 发送请求。调用RestHighLevelClient#indices().xxx方法，xxx
        是create、exists、delete
    - RestClient文档操作的基本步骤
        - 初始化RestHighLevelClient
        - 创建XxxRequest。xxx是Index、Get、Update、Delete
        - 准备参数（Index和Update时需要）
        - 发送请求。调用RestHighLevelClient#.xxx()方法，xxx是index、get、update、detele
        - 解析结果（Get时需要）
- DSL查询语法
    - 查询DSL的基本语法
        - GET /索引库名/_search
        - {"query": {"查询类型": {"FIELD": "TEXT"}}}
    - 全文检索查询
        - match和multi_match的区别
            - match：根据一个字段查询
            - 根据多个字段查询，参与查询字段越多，查询性能越差
    - 精确查询
        - 常见精确查询
            - term查询：根据词条精确匹配，一般搜索keyword类型、数值类型、布尔类型、日期类字段
            - range查询：根据数值范围查询，可以是数数值、日期的范围
    - 地理查询
        - 常见地理查询
            - geo_bounding_box：查询geo_point值落在某个矩形范围的所有文档
            - geo_distance：查询到指定中心点小于某个距离值的所有文档
    - 相关性算分
        - elaticsearch中的相关性打分算法
            - TF-IDF：在elaticsearch5.0之前，会随着词频增加而越来越大
            - BM25：在elaticsearch5.0之后，会随着词频增加而增大，但增长曲线会趋于水平
        - Functon Score Query定义的三要素
            - 过滤条件：哪些文档要加分
            - 算分函数：如何计算function score
            - 加权方式：function score与query score如何运算
        - Boolean Query查询的逻辑关系
            - must：必须匹配的条件，可以理解为 "与"
            - should：选择性匹配的条件，可以理解为 "或"
            - must_not：必须不匹配的条件，不参与打分
            - filter：必须匹配的条件，不参与打分
- 搜索结果处理
    - 排序
        - elaticsearch支持对搜索结果排序，默认是根据相关度算分（_score）来排序。可以排序字段类型有：keyword类型、数值类型、地理坐标类型、日期类型
    - 分页
        - from + size
            - 优点：支持随机翻页
            - 缺点：深度分页问题，默认查询上限（from + size）是10000
            - 场景：百度、京东、谷歌、淘宝这样的随机翻页搜索
        - after search
            - 优点：没有查询上限（单次查询的size不超过10000）
            - 只能向后逐页查询，不支持随机翻页
            - 场景：没有随机翻页需求的搜索，例如手机向下滚动翻页
        - scroll
            - 优点：没有查询上限（单次查询的size不超过10000）
            - 缺点：会有额外内存消耗，并且搜索结果是非实时的
            - 场景：海量数据的获取和迁移。从ES7.1开始不推荐，建议用after search方案
    - 高亮
        - 就是在搜索结果中把搜索关键字突出显示
        - 原理
            - 将搜索结果中的关键字用标签标记出来
            - 在页面中给标签添加css样式
    - RestClient查询文档
        - 查询的基本步骤
            - 创建SearchRequest对象
            - 准备Request.source()，也就是DSL
                - QueryBuilders来构建查询条件
                - 传入Request.source()的query方法
            - 发送请求，得到结果
            - 解析结果（参考JSON结果，从外到内，逐层解析）
        - 所有搜索DSL的构建，记住一个API：SearchRequest的source()方法
- 数据聚合
    - 聚合的定义
        - 聚合是对文档数据的统计、分析、计算
    - 聚合的常见种类
        - Bucket：对文档数据分组，并统计每组数量
        - Metric：对文档数据做计算，例如avg
        - Pipeline：基于其他聚合结果再做聚合
    - 参与聚合的字段类型
        - keyword
        - 数值
        - 日期
        - 布尔
    - DSL实现聚合
        - aggs代表聚合，与query同级
    - 聚合必须的三要素
        - 聚合名称
        - 聚合类型
        - 集合字段
    - 聚合可配置属性
        - size：指定聚合结果数量
        - order：指定聚合结果排序方式
        - field：指定聚合字段
- 自动补全
![analyzer]()
    - 自定义分词器
        - 创建自定义分词器
            - 创建索引库时，在settings中配置，可以包含三部分
            - character filter
            - tokenizer
            - filter
        - 拼音分词器注意事项
            - 为了避免搜索到同音字是，搜索时不要使用拼音分词器
    - 自动补全对字段的要求
        - 类型时completion类型
        - 字段值是多词条的数组
- 数据同步
    - 方式一：同步调用
        - 优点：实现简单，粗暴
        - 缺点：业务耦合度高
    - 方式二：异步通知
        - 优点：低耦合，实现难度一般
        - 缺点：依赖mq的可靠性
    - 方式三：监听binlog
        - 优点：完全解决服务间耦合
        - 缺点：开启binlog增加数据库负担、实现复杂度高
- ES集群
![es集群结构]()
    - ES集群职责及脑裂
    ![es集群节点角色]()
        - ES集群的脑裂
            - 默认情况下，每个节点都是master eligible节点，因此一旦master节点宕机，其他候选节点会选举一个成为主节点。当主节点与其他节点网络故障时，可能发生脑裂问题
            - 为了避免脑裂，需要要求选票超过 (eligible节点数量 + 1) / 2 才能当选为主，因此eligible节点数量最好是奇数。对应配置项是discovery.zen.minimum_master_nodes，在es7.0以后，已经成为默认配置，因此一般不会发生脑裂问题
        - master eligible节点的作用
            - 参与集群选主
            - 主节点可以管理集群状态、管理分片信息、处理创建和删除索引库的请求
        - data节点的作用
            - 数据的CRUD
        - coordinator节点的作用
            - 路由请求到其他节点
            - 合并查询到的结果，返回给用户
    - 分布式新增和查询流程
        - 分布式新增确定分片
            - coordinating node根据id做hash运算，得到结果对shard数量取余，余数就是对应的分片      
        - 分布式查询
            - 分散阶段：coordinating node将查询请求分发给不同分片
            - 收集阶段：将查询结果汇总到coordinating node，整理并返回给用户
    - 故障转移
        - master宕机后，EligibleMaster选举为新的主节点
        - master节点监控分片、节点状态，将故障节点上的分片转移到正常节点，确保数据安全

##### ___以上ES的DSL语法可参考 [ES-DSL.md]() 文档___
##### ___以上ES分布式搜索技术可参考 Hotel-Demo 和 Hotel-Admin 项目___

---

参考学习资料：[微服务](https://www.bilibili.com/video/BV1LQ4y127n4?p=1&vd_source=a6541efd5c43d30c410c9e45054c9b89)