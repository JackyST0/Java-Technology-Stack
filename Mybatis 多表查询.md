### Mybatis 多表查询：
* ##### 一对一:... left join ... on ... where .../... right join ... on ... where ...,association
* ##### 多对一:... left join ... on ... where .../... right join ... on ... where ...,association(还可用分布查询)
* ##### 一对多:... left join ... on ... where .../... right join ... on ... where ...,collection(还可用分布查询)
* ##### 多对多:... left join "中间表" on ... left join ... on .../... right join "中间表" on ... right join ... on ...,collection

### 数据库查询代码思路：
##### 查询需求 -> 查看数据库对应表字段信息 -> 得出表与表的关系 -> 编写sql语句 -> 输出结果完成需求 
---
参考文案：[Mybatis](https://www.wolai.com/dMH3wn6ZzJgLujGSarrhB6)
