### RabbitMQ：

#### MQ概述：
* MQ全称 Message Queue（消息队列），是在消息的传输过程中保存消息的容器，也可称为存储消息的中间件。多用于分布式系统之间进行通信。
* 分布式系统通信的两种方式：直接远程调用和借助第三方完成间接通信。
* 发送方称为生产者，接收方称为消费者。

---

#### MQ的优势和劣势：
* 优势
    - 应用解耦：提高系统容错性和可维护性。
    - 异步提速：提升用户体验和系统吞吐量。
    - 削峰填谷：提高系统稳定性。
* 劣势
    - 系统可用性降低。
    - 系统复杂度提高。
    - 一致性问题。
---
#### RabbitMQ简介：
![rabbitmq1](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/RabbitMQ-1.png)
![rabbitmq2](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/RabbitMQ-2.png)
![rabbitmq3](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/RabbitMQ-3.png)

---

#### RabbitMQ工作模式：
* ##### 简单模式
    ![simple](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E7%AE%80%E5%8D%95%E6%A8%A1%E5%BC%8F.png)  
小结：
    - P：生产者，也就是要发送消息的程序。
    - C：消费者，消息的接收者，会一直等待消息到来。
    - queue：消息队列，图中红色部分，类似一个邮箱，可以缓存消息；生产者向其中投递消息，消费者从其中取出消息。

* ##### Work queues 工作队列模式
    ![work queues](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Work%20queues.png)      
小结：
    - 在一个队列中如果有多个消费者，那么消费者之间对于同一个消息的关系是竞争的关系。
    - Work Queues 对于任务过重或任务较多情况使用工作队列可以提高任务处理的速度。例如：短信服务部署多个，只需要一个节点成功发送即可。

* ##### Pub/Sub 订阅模式
    ![pub_sub](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Pub_Sub.png)
小结：
    - 在订阅模型中新增了一个 Exchange 交换机角色，并且如果要使用 Pub/Sub 模式，其交换机的类型要设置为"Fanout"才行。
    - 跟 Work queues 的工作模式类似，区别是 Work queues 由很多个消费者监听同一个队列，并且只能由一个消费者收到；而 Pub/Sub 模式则是有很多个消费者，每一个消费者都监听自己的队列，消息到来之后每一个人都可以收到这个消息。

* ##### Routing 路由模式
    ![routing](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Routing.png)
小结：
    - Routing 模式要求队列在绑定交换机时要指定 routing key，并且交换机的类型要设置为"Direct"，这样消息才会转发到符合 routing key 的队列。
 
* ##### Topics 通配符模式
    ![topics](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Topics.png)
小结：
    - 通配符规则：# 匹配一个或多个词，* 匹配不多不少恰好一个词。且记得交换机模式要设置为"Topic"才能使用通配符模式。
    - Topics 模式可以实现 Pub/Sub 发布与订阅模式和 Routing 路由模式的功能，只是 Topics 在配置 routing key 的时候可以使用通配符，显得更加灵活。

* ##### 工作模式总结
    - 简单模式 HelloWorld
        + 一个生产者、一个消费者，不需要设置交换机（使用RabbitMQ默认的交换机） 。
    - 工作队列模式 Work Queue
        + 一个生产者、多个消费者（竞争关系），不需要设置交换机（使用RabitMQ默认的交换机）。
    - 发布订阅模式 Publish/Subscribe
        + 需要设置类型为 fanout 的交换机，并且交换机和队列进行绑定，当发送消息到交换机后，交换机会将消息发送到绑定的队列。
    - 路由模式 Routing
        + 需要设置类型为 direct 的交换机，交换机和队列进行绑定，并且指定 routing key ，当发送消息到交换机后，交换机会根据 routing key 将消息发送到对应的队列。
    - 通配符模式 Topics
        + 需要设置类型为 topic 的交换机，交换机和队列进行绑定，并且指定通配符方式的 routing key，当发送消息到交换机后，交换机会根据 routing key 将消息发送到对应的队列。

---

#### RabbitMQ高级特性：
* ##### 消息的可靠投递
    - Confirm 确认模式、Return 退回模式
    ![confirm/return](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Confirm_Return.png)
    - Consumer Ack
    ![consumer ack](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Consumer%20Ack.png)
    小结：
        + 持久化：exchange 要持久化、queue 要持久化、message 要持久化。
        + 生产方确认Confirm。
        + 消费方确认ACK。
        + Broker 高可用。

* ##### 消费端限流
    - 监听器不加 prefetch 以及消费端不签收：
        + 生产者发送十条数据，并观察消费者，可见一次性获取了所有的十条消息。
    - 监听器加上 prefetch 以及消费端不签收：
        + 生产者重新发送10条数据，可见消费者仅接收到一条消息。
    - 监听器加上 prefetch 以及消费端签收：
        + 打开消费端签收，可以观察到消费端依次消费每次打印一行。

* ##### TTL
    ![ttl](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/TTL.png)
    - 设置整个队列的过期时间：
        + 队列过期时间到后消息消失。
    - 设置单个消息的过期时间：
        + 消息过期时间到后消息消失。
    - 设置单个消息非队列顶部设置超时时间：
        + 虽然设置了超时时间当由于此单个消息没有位于顶部，因此消息过期时间到后消息不会消失。    
    - 小结：
        + 如果设置了消息的过期时间，也设置了队列的过期时间，它以时间短的为准。
        + 队列过期后，会将队列所有消息全部移除。
        + 消息过期后，只有消息在队列顶端，才会判断其是否过期(移除掉)。 

* ##### 死信队列
    ![死信队列1](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%AD%BB%E4%BF%A1%E9%98%9F%E5%88%97-1.png)
    ![死信队列2](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%AD%BB%E4%BF%A1%E9%98%9F%E5%88%97-2.png)
    ![死信队列3](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%AD%BB%E4%BF%A1%E9%98%9F%E5%88%97-3.png)

* ##### 延迟队列
    ![延迟队列1](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E5%BB%B6%E8%BF%9F%E9%98%9F%E5%88%97-1.png)
    ![延迟队列2](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E5%BB%B6%E8%BF%9F%E9%98%9F%E5%88%97-2.png)

* ##### 日志与监控
    - RabbbitMQ日志
    ![日志](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/RabbitMQ%E6%97%A5%E5%BF%97.png)
    - web管控台监控
    ![管控台监控](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E7%AE%A1%E6%8E%A7%E5%8F%B0%E7%9B%91%E6%8E%A7.png)
    - 系统命令行监控
    ![命令行监控](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E5%91%BD%E4%BB%A4%E8%A1%8C%E7%9B%91%E6%8E%A7.png)

* ##### 消息追踪
    ![消息追踪1](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%B6%88%E6%81%AF%E8%BF%BD%E8%B8%AA-1.png)
    - Firehose
    ![消息追踪2](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%B6%88%E6%81%AF%E8%BF%BD%E8%B8%AA-2.png)
    - rabbitmq_tracing
    ![消息追踪3](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%B6%88%E6%81%AF%E8%BF%BD%E8%B8%AA-3.png)

---

#### RabbitMQ应用问题：
* ##### 消息可靠性保障
    - 消息补偿机制
    ![消息补偿](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E6%B6%88%E6%81%AF%E8%A1%A5%E5%81%BF%E6%9C%BA%E5%88%B6.png)

* ##### 消息幂等性保障
    ![幂等性](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E5%B9%82%E7%AD%89%E6%80%A7.png)
    - 乐观锁解决方案
    ![乐观锁](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/%E4%B9%90%E8%A7%82%E9%94%81%E6%9C%BA%E5%88%B6.png)

---

参考文案：[RabbitMQ](https://weishao-996.github.io/2022/11/21/%E9%BB%91%E9%A9%AC%E7%A8%8B%E5%BA%8F%E5%91%98-RabbitMQ/)
