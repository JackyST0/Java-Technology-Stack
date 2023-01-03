# MySQL:

### MySQL 安装：
```
(注：如果未设置系统环境变量，命令要进入MySQL目录的bin文件夹下输入才有效且要用管理员身份打开命令框)
初始化数据库：mysqld --initialize --console

安装数据库命令：mysqld install

启动系统mysql服务命令：net start mysql服务名
关闭系统mysql服务命令：net stop mysql服务名
（注：mysql服务名，根据自己安装时定义的为准，例如我的是mysql）

修改默认密码：ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '新密码';

创建root用户权限默认密码可供外部访问：create user 'root'@'%'IDENTIFIED WITH mysql_native_password BY '新密码';

添加用户：INSERT INTO user 
          (host, user, password, 
           select_priv, insert_priv, update_priv) 
           VALUES ('localhost', '用户名', 
           PASSWORD('密码'), 'Y', 'Y', 'Y');

另外一种添加用户的方法为通过SQL的 GRANT 命令，以下命令会给指定数据库TUTORIALS添加用户 zara ，密码为 zara123 ：
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
    -> ON TUTORIALS.*
    -> TO 'zara'@'localhost'
    -> IDENTIFIED BY 'zara123';

列出 MySQL 数据库管理系统的数据库列表：SHOW DATABASES

显示指定数据库的所有表：SHOW TABLES

显示数据表的属性，属性类型，主键信息 ，是否为 NULL，默认值等其他信息：SHOW COLUMNS FROM 数据表

显示数据表的详细索引信息，包括PRIMARY KEY（主键）：SHOW INDEX FROM 数据表

该命令将输出Mysql数据库管理系统的性能及统计信息：SHOW TABLE STATUS [FROM db_name] [LIKE 'pattern'] \G
mysql> SHOW TABLE STATUS  FROM RUNOOB;   # 显示数据库 RUNOOB 中所有表的信息
mysql> SHOW TABLE STATUS from RUNOOB LIKE 'runoob%';     # 表名以runoob开头的表的信息
mysql> SHOW TABLE STATUS from RUNOOB LIKE 'runoob%'\G;   # 加上 \G，查询结果按列打印

刷新权限：flush privileges;
(注: MySQL 的SQL语句以分号 (;) 作为结束标识。)
```

### MySQL 连接：
```
使用mysql二进制方式连接mysql：mysql -h 主机地址 -u用户名 -p用户密码（注：-u和-p后面直接跟用户名密码，中间不能有空格）
使用 PHP 脚本连接 MySQL：mysqli_connect(host, username, password, dbname,port, socket);
参数	        描述
host	        可选。规定主机名或 IP 地址。
username	可选。规定 MySQL 用户名。
password	可选。规定 MySQL 密码。
dbname	        可选。规定默认使用的数据库。
port	        可选。规定尝试连接到 MySQL 服务器的端口号。
socket	        可选。规定 socket 或要使用的已命名 pipe。
使用 PHP 的 mysqli_close() 函数来断开与 MySQL 数据库的链接：mysqli_close (connection)
```

### MySQL 创建数据库：
```
root权限使用 create 命令创建数据库：CREATE DATABASE 数据库名;
普通权限使用 mysqladmin 创建数据库：mysqladmin -u root -p create 数据库名
使用 PHP脚本 创建数据库：mysqli_query(connection,query,resultmode);
参数	            描述
connection	 必需。规定要使用的 MySQL 连接。
query	         必需，要操作的SQL语句。
resultmode	 可选。一个常量。可以是下列值中的任意一个：
                            MYSQLI_USE_RESULT（如果需要检索大量数据，请使用这个）
                            MYSQLI_STORE_RESULT（默认）
```

### MySQL 删除数据库：
```
root权限使用 drop 命令删除数据库：drop database <数据库名>;
普通权限使用 mysqladmin 删除数据库:mysqladmin -u root -p drop <数据库名>
使用PHP脚本删除数据库：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL 选择数据库：
```
选择要操作的Mysql数据库：USE 数据库名
使用PHP脚本选择MySQL数据库：mysqli_select_db(connection,dbname);
参数	        描述
connection	必需。规定要使用的 MySQL 连接。
dbname	        必需，规定要使用的默认数据库。
```

### MySQL 创建数据表：
```
通过命令提示符创建表：CREATE TABLE table_name (column_name column_type);
使用PHP脚本创建数据表：mysqli_query(connection,query,resultmode);
参数	            描述
connection	 必需。规定要使用的 MySQL 连接。
query	         必需，要操作的SQL语句。
resultmode	 可选。一个常量。可以是下列值中的任意一个：
                            MYSQLI_USE_RESULT（如果需要检索大量数据，请使用这个）
                            MYSQLI_STORE_RESULT（默认）
```

### MySQL 删除数据表：
```
在命令提示窗口中删除数据表：DROP TABLE table_name;
使用PHP脚本删除数据表：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL 插入数据：
```
通过命令提示窗口插入数据(注：如果数据是字符型，必须使用单引号或者双引号，如："value"。):
INSERT INTO table_name ( field1, field2,...fieldN )
                       VALUES
                       ( value1, value2,...valueN );
使用PHP脚本插入数据（注：对于含有中文的数据插入，需要添加 mysqli_query(connection , "set names utf8"); 语句。）：mysqli_query(connection,query,resultmode);
参数	            描述
connection	 必需。规定要使用的 MySQL 连接。
query	         必需，要操作的SQL语句。
resultmode	 可选。一个常量。可以是下列值中的任意一个：
                            MYSQLI_USE_RESULT（如果需要检索大量数据，请使用这个）
                            MYSQLI_STORE_RESULT（默认）
```

### MySQL 查询数据：
```
通过命令提示符获取数据：
SELECT column_name,column_name
FROM table_name
[WHERE Clause]
[LIMIT N][ OFFSET M]

查询语句中你可以使用一个或者多个表，表之间使用逗号(,)分割，并使用WHERE语句来设定查询条件。
SELECT 命令可以读取一条或者多条记录。
你可以使用星号（*）来代替其他字段，SELECT语句会返回表的所有字段数据
你可以使用 WHERE 语句来包含任何条件。
你可以使用 LIMIT 属性来设定返回的记录数。
你可以通过OFFSET指定SELECT语句开始查询的数据偏移量。默认情况下偏移量为0。

使用PHP脚本来获取数据(提示：可以通过 PHP 函数 mysqli_fetch_array() 或 mysqli_fetch_assoc() 来使用或输出所有查询的数据。):mysqli_query(connection,query,resultmode);
参数	            描述
connection	 必需。规定要使用的 MySQL 连接。
query	         必需，要操作的SQL语句。
resultmode	 可选。一个常量。可以是下列值中的任意一个：
                            MYSQLI_USE_RESULT（如果需要检索大量数据，请使用这个）
                            MYSQLI_STORE_RESULT（默认）

通过 PHP 函数实现内存释放：mysqli_free_result()
```

### MySQL WHERE 子句：
```
从命令提示符中读取特定数据(提示：MySQL 的 WHERE 子句的字符串比较是不区分大小写的。你可以使用 BINARY 关键字来设定 WHERE 子句的字符串比较是区分大小写的。)：SELECT field1, field2,...fieldN FROM table_name1, table_name2...
[WHERE condition1 [AND [OR]] condition2.....];
使用PHP脚本读取数据：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL UPDATE 更新：
```
使用命令行更新数据：UPDATE table_name SET field1=new-value1, field2=new-value2 [WHERE Clause]
使用PHP脚本更新数据：mysqli_query(connection,query,resultmode);
参数	            描述
connection	 必需。规定要使用的 MySQL 连接。
query	         必需，要操作的SQL语句。
resultmode	 可选。一个常量。可以是下列值中的任意一个：
                            MYSQLI_USE_RESULT（如果需要检索大量数据，请使用这个）
                            MYSQLI_STORE_RESULT（默认）
```

### MySQL DELETE 语句：
```
从命令行中删除数据：DELETE FROM table_name [WHERE Clause]
使用 PHP 脚本删除数据：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL LIKE 子句：
```
SQL LIKE 子句中使用百分号 %字符来表示任意字符，类似于UNIX或正则表达式中的星号 *。
如果没有使用百分号 %, LIKE 子句与等号 = 的效果是一样的。           

在命令提示符中使用 LIKE 子句:
SELECT field1, field2,...fieldN 
FROM table_name
WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
在PHP脚本中使用 LIKE 子句：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL UNION 操作符：
```
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions]
UNION [ALL | DISTINCT]
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions];
参数
expression1, expression2, ... expression_n: 要检索的列。
tables: 要检索的数据表。
WHERE conditions: 可选， 检索条件。
DISTINCT: 可选，删除结果集中重复的数据。默认情况下 UNION 操作符已经删除了重复数据，所以 DISTINCT 修饰符对结果没啥影响。
ALL: 可选，返回所有结果集，包含重复数据。
```

### MySQL 排序：
```
在命令提示符中使用 ORDER BY 子句（提示：你可以使用 ASC 或 DESC 关键字来设置查询结果是按升序或降序排列。 默认情况下，它是按升序排列。）：
SELECT field1, field2,...fieldN FROM table_name1, table_name2...
ORDER BY field1 [ASC [DESC][默认 ASC]], [field2...] [ASC [DESC][默认 ASC]]
在 PHP 脚本中使用 ORDER BY 子句：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL GROUP BY 语句：
```
GROUP BY 语句根据一个或多个列对结果集进行分组。
在分组的列上我们可以使用 COUNT, SUM, AVG,等函数。

GROUP BY 语法：
SELECT column_name, function(column_name)
FROM table_name
WHERE column_name operator value
GROUP BY column_name;

mysql> SELECT name, COUNT(*) FROM   employee_tbl GROUP BY name;
+--------+----------+
| name   | COUNT(*) |
+--------+----------+
| 小丽 |        1 |
| 小明 |        3 |
| 小王 |        2 |
+--------+----------+
3 rows in set (0.01 sec)

使用 WITH ROLLUP：
WITH ROLLUP 可以实现在分组统计数据基础上再进行相同的统计（SUM,AVG,COUNT…）。
mysql> SELECT name, SUM(signin) as signin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
+--------+--------------+
| name   | signin_count |
+--------+--------------+
| 小丽 |            2 |
| 小明 |            7 |
| 小王 |            7 |
| NULL   |           16 |
+--------+--------------+
4 rows in set (0.00 sec)

我们可以使用 coalesce 来设置一个可以取代 NUll 的名称，coalesce 语法：select coalesce(a,b,c);
mysql> SELECT coalesce(name, '总数'), SUM(signin) as signin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
+--------------------------+--------------+
| coalesce(name, '总数') | signin_count |
+--------------------------+--------------+
| 小丽                   |            2 |
| 小明                   |            7 |
| 小王                   |            7 |
| 总数                   |           16 |
+--------------------------+--------------+
4 rows in set (0.01 sec)
```

### MySQL 连接的使用:
```
INNER JOIN（内连接,或等值连接）：获取两个表中字段匹配关系的记录。
LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应匹配的记录。
RIGHT JOIN（右连接）： 与 LEFT JOIN 相反，用于获取右表所有记录，即使左表没有对应匹配的记录。

在 PHP 脚本中使用 JOIN：用法同上PHP，只需修改参数 query 中的语句即可
```

### MySQL NULL 值处理：
```
我们已经知道 MySQL 使用 SQL SELECT 命令及 WHERE 子句来读取数据表中的数据,但是当提供的查询条件字段为 NULL 时，该命令可能就无法正常工作。为了处理这种情况，MySQL提供了三大运算符:
IS NULL: 当列的值是 NULL,此运算符返回 true。
IS NOT NULL: 当列的值不为 NULL, 运算符返回 true。
<=>: 比较操作符（不同于 = 运算符），当比较的的两个值相等或者都为 NULL 时返回 true。

使用 PHP 脚本处理 NULL 值:PHP 脚本中你可以在 if...else 语句来处理变量是否为空，并生成相应的条件语句,再通过函数mysqli_query()和mysqli_fetch_array()查看比较结果。
```

### MySQL 正则表达式：
```
模式	       描述
^	        匹配输入字符串的开始位置。如果设置了 RegExp 对象的 Multiline 属性，^ 也匹配 '\n' 或 '\r' 之后的位置。
$	        匹配输入字符串的结束位置。如果设置了RegExp 对象的 Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置。
.	        匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用像 '[.\n]' 的模式。
[...]	        字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。
[^...]	        负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。
p1|p2|p3	匹配 p1 或 p2 或 p3。例如，'z|food' 能匹配 "z" 或 "food"。'(z|f)ood' 则匹配 "zood" 或 "food"。
*	        匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。
+	        匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。
{n}	        n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。
{n,m}	        m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。
```

### MySQL 事务：
```
在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务（即创建数据表时要设置engine=innodb）。
事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。
事务用来管理 insert,update,delete 语句

一般来说，事务是必须满足4个条件（ACID）：：原子性（Atomicity，或称不可分割性）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。
原子性：一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
一致性：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
隔离性：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
持久性：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。

事务控制语句：
BEGIN 或 START TRANSACTION 显式地开启一个事务；
COMMIT 也可以使用 COMMIT WORK，不过二者是等价的。COMMIT 会提交事务，并使已对数据库进行的所有修改成为永久性的；
ROLLBACK 也可以使用 ROLLBACK WORK，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改；
SAVEPOINT identifier，SAVEPOINT 允许在事务中创建一个保存点，一个事务中可以有多个 SAVEPOINT；
RELEASE SAVEPOINT identifier 删除一个事务的保存点，当没有指定的保存点时，执行该语句会抛出一个异常；
ROLLBACK TO identifier 把事务回滚到标记点；
SET TRANSACTION 用来设置事务的隔离级别。InnoDB 存储引擎提供事务的隔离级别有READ UNCOMMITTED、READ COMMITTED、REPEATABLE READ 和 SERIALIZABLE。

MYSQL 事务处理主要有两种方法：
1、用 BEGIN, ROLLBACK, COMMIT来实
BEGIN 开始一个事务
ROLLBACK 事务回滚
COMMIT 事务确认
2、直接用 SET 来改变 MySQL 的自动提交模式:
SET AUTOCOMMIT=0 禁止自动提交
SET AUTOCOMMIT=1 开启自动提交

PHP中使用事务实例：
mysqli_begin_transaction(connection);     // 开始事务定义
mysqli_query(connection, "ROLLBACK");     // 执行回滚
mysqli_commit(connection);                //执行事务 
```

### MySQL ALTER命令：
```
删除，添加或修改表字段：
如下命令使用了 ALTER 命令及 DROP 子句来删除以上创建表的 i 字段：ALTER TABLE testalter_tbl  DROP i;（注：如果数据表中只剩余一个字段则无法使用DROP来删除字段。）
MySQL 中使用 ADD 子句来向数据表中添加列，如下实例在表 testalter_tbl 中添加 i 字段，并定义数据类型:ALTER TABLE testalter_tbl ADD i INT;（提示：执行以上命令后，i 字段会自动添加到数据表字段的末尾。）
如果你需要指定新增字段的位置，可以使用MySQL提供的关键字 FIRST (设定位第一列)， AFTER 字段名（设定位于某个字段之后）：
ALTER TABLE testalter_tbl ADD i INT FIRST;
ALTER TABLE testalter_tbl ADD i INT AFTER c;
（提示：FIRST 和 AFTER 关键字可用于 ADD 与 MODIFY 子句，所以如果你想重置数据表字段的位置就需要先使用 DROP 删除字段然后使用 ADD 来添加字段并设置位置。）

修改字段类型及名称：
如果需要修改字段类型及名称, 你可以在ALTER命令中使用 MODIFY 或 CHANGE 子句 。例如，把字段 c 的类型从 CHAR(1) 改为 CHAR(10)，可以执行以下命令:
ALTER TABLE testalter_tbl MODIFY c CHAR(10);
使用 CHANGE 子句, 语法有很大的不同。 在 CHANGE 关键字之后，紧跟着的是你要修改的字段名，然后指定新字段名及类型。尝试如下实例：
ALTER TABLE testalter_tbl CHANGE i j BIGINT;
ALTER TABLE testalter_tbl CHANGE j j INT;

ALTER TABLE 对 Null 值和默认值的影响：
当你修改字段时，你可以指定是否包含值或者是否设置默认值。以下实例，指定字段 j 为 NOT NULL 且默认值为100 。
ALTER TABLE testalter_tbl MODIFY j BIGINT NOT NULL DEFAULT 100;
（提示：如果你不设置默认值，MySQL会自动设置该字段默认为 NULL。）

修改字段默认值：
你可以使用 ALTER 来修改字段的默认值，尝试以下实例：
ALTER TABLE testalter_tbl ALTER i SET DEFAULT 1000;
你也可以使用 ALTER 命令及 DROP子句来删除字段的默认值，如下实例：
ALTER TABLE testalter_tbl ALTER i DROP DEFAULT;
修改数据表类型，可以使用 ALTER 命令及 TYPE 子句来完成。尝试以下实例，我们将表 testalter_tbl 的类型修改为 MYISAM ：
ALTER TABLE testalter_tbl ENGINE = MYISAM;
（提示：查看数据表类型可以使用 SHOW TABLE STATUS 语句。）

修改表名：
如果需要修改数据表的名称，可以在 ALTER TABLE 语句中使用 RENAME 子句来实现。尝试以下实例将数据表 testalter_tbl 重命名为 alter_tbl：
ALTER TABLE testalter_tbl RENAME TO alter_tbl;
```

### MySQL 索引：
```
普通索引：
创建索引，这是最基本的索引，它没有任何限制。它有以下几种创建方式：
CREATE INDEX indexName ON table_name (column_name)
（注：如果是CHAR，VARCHAR类型，length可以小于字段实际长度；如果是BLOB和TEXT类型，必须指定 length。）
修改表结构(添加索引)：
ALTER table tableName ADD INDEX indexName(columnName)
创建表的时候直接指定：
CREATE TABLE mytable(  
    ID INT NOT NULL,   
    username VARCHAR(16) NOT NULL,  
    INDEX [indexName] (username(length))  
 );  
删除索引的语法：
DROP INDEX [indexName] ON mytable; 

唯一索引
它与前面的普通索引类似，不同的就是：索引列的值必须唯一，但允许有空值。如果是组合索引，则列值的组合必须唯一。它有以下几种创建方式：
创建索引
CREATE UNIQUE INDEX indexName ON mytable(username(length)) 
修改表结构
ALTER table mytable ADD UNIQUE [indexName] (username(length))
创建表的时候直接指定
CREATE TABLE mytable(  
    ID INT NOT NULL,   
    username VARCHAR(16) NOT NULL,  
    UNIQUE [indexName] (username(length))  
 );  

使用ALTER 命令添加和删除索引：
有四种方式来添加数据表的索引：
ALTER TABLE tbl_name ADD PRIMARY KEY (column_list): 该语句添加一个主键，这意味着索引值必须是唯一的，且不能为NULL。
ALTER TABLE tbl_name ADD UNIQUE index_name (column_list): 这条语句创建索引的值必须是唯一的（除了NULL外，NULL可能会出现多次）。
ALTER TABLE tbl_name ADD INDEX index_name (column_list): 添加普通索引，索引值可出现多次。
ALTER TABLE tbl_name ADD FULLTEXT index_name (column_list):该语句指定了索引为 FULLTEXT ，用于全文索引。
你还可以在 ALTER 命令中使用 DROP 子句来删除索引：ALTER TABLE testalter_tbl DROP INDEX c;

使用 ALTER 命令添加和删除主键：
主键作用于列上（可以一个列或多个列联合主键），添加主键索引时，你需要确保该主键默认不为空（NOT NULL）。实例如下：
ALTER TABLE testalter_tbl MODIFY i INT NOT NULL;
ALTER TABLE testalter_tbl ADD PRIMARY KEY (i);
你也可以使用 ALTER 命令删除主键：ALTER TABLE testalter_tbl DROP PRIMARY KEY;（提示：删除主键时只需指定PRIMARY KEY，但在删除索引时，你必须知道索引名。）

显示索引信息：
你可以使用 SHOW INDEX 命令来列出表中的相关的索引信息。可以通过添加 \G 来格式化输出信息:SHOW INDEX FROM table_name\G
```

### MySQL 临时表：
```
以下展示了使用MySQL 临时表的简单实例，以下的SQL代码可以适用于PHP脚本的mysql_query()函数：
CREATE TEMPORARY TABLE SalesSummary (
    -> product_name VARCHAR(50) NOT NULL
    -> , total_sales DECIMAL(12,2) NOT NULL DEFAULT 0.00
    -> , avg_unit_price DECIMAL(7,2) NOT NULL DEFAULT 0.00
    -> , total_units_sold INT UNSIGNED NOT NULL DEFAULT 0
);
　
删除MySQL 临时表：
默认情况下，当你断开与数据库的连接后，临时表就会自动被销毁。当然你也可以在当前MySQL会话使用 DROP TABLE 命令来手动删除临时表：
DROP TABLE SalesSummary;
```

### MySQL 复制表：
```
使用 SHOW CREATE TABLE 命令获取创建数据表(CREATE TABLE) 语句，该语句包含了原数据表的结构，索引等。
复制以下命令显示的SQL语句，修改数据表名，并执行SQL语句，通过以上命令 将完全的复制数据表结构。
如果你想复制表的内容，你就可以使用 INSERT INTO ... SELECT 语句来实现。

尝试以下实例来复制表 runoob_tbl 。
步骤一：获取数据表的完整结构。
mysql> SHOW CREATE TABLE runoob_tbl \G;
*************************** 1. row ***************************
       Table: runoob_tbl
Create Table: CREATE TABLE `runoob_tbl` (
  `runoob_id` int(11) NOT NULL auto_increment,
  `runoob_title` varchar(100) NOT NULL default '',
  `runoob_author` varchar(40) NOT NULL default '',
  `submission_date` date default NULL,
  PRIMARY KEY  (`runoob_id`),
  UNIQUE KEY `AUTHOR_INDEX` (`runoob_author`)
) ENGINE=InnoDB 
1 row in set (0.00 sec)
ERROR:
No query specified

步骤二：修改SQL语句的数据表名，并执行SQL语句。
mysql> CREATE TABLE `clone_tbl` (
  -> `runoob_id` int(11) NOT NULL auto_increment,
  -> `runoob_title` varchar(100) NOT NULL default '',
  -> `runoob_author` varchar(40) NOT NULL default '',
  -> `submission_date` date default NULL,
  -> PRIMARY KEY  (`runoob_id`),
  -> UNIQUE KEY `AUTHOR_INDEX` (`runoob_author`)
-> ) ENGINE=InnoDB;
Query OK, 0 rows affected (1.80 sec)

步骤三：执行完第二步骤后，你将在数据库中创建新的克隆表 clone_tbl。 如果你想拷贝数据表的数据你可以使用 INSERT INTO... SELECT 语句来实现。
mysql> INSERT INTO clone_tbl (runoob_id,
    ->                        runoob_title,
    ->                        runoob_author,
    ->                        submission_date)
    -> SELECT runoob_id,runoob_title,
    ->        runoob_author,submission_date
    -> FROM runoob_tbl;
Query OK, 3 rows affected (0.07 sec)
Records: 3  Duplicates: 0  Warnings: 0
执行以上步骤后，会完整的复制表的内容，包括表结构及表数据。
```

### MySQL 元数据：
```
你可能想知道MySQL以下三种信息：
查询结果信息： SELECT, UPDATE 或 DELETE语句影响的记录数。
数据库和数据表的信息： 包含了数据库及数据表的结构信息。
MySQL服务器信息： 包含了数据库服务器的当前状态，版本号等。

数据库和数据表列表：
你可以很容易的在MySQL服务器中获取数据库和数据表列表。 如果你没有足够的权限，结果将返回 null。你也可以使用 SHOW TABLES 或 SHOW DATABASES 语句来获取数据库和数据表列表。

获取服务器元数据：
以下命令语句可以在 MySQL 的命令提示符使用，也可以在脚本中 使用，如PHP脚本。
命令	                描述
SELECT VERSION( )	服务器版本信息
SELECT DATABASE( )	当前数据库名 (或者返回空)
SELECT USER( )	        当前用户名
SHOW STATUS	        服务器状态
SHOW VARIABLES	        服务器配置变量
```

### MySQL 序列使用：
```
MySQL 序列是一组整数：1, 2, 3, ...，由于一张数据表只能有一个字段自增主键， 如果你想实现其他字段也实现自动增加，就可以使用MySQL序列来实现。

使用 AUTO_INCREMENT：
MySQL 中最简单使用序列的方法就是使用 MySQL AUTO_INCREMENT 来定义序列。以下实例中创建了数据表 insect， insect 表中 id 无需指定值可实现自动增长：
CREATE TABLE insect
    -> (
    -> id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    -> PRIMARY KEY (id),
    -> name VARCHAR(30) NOT NULL, # type of insect
    -> date DATE NOT NULL, # date collected
    -> origin VARCHAR(30) NOT NULL # where collected
);

重置序列：
如果你删除了数据表中的多条记录，并希望对剩下数据的AUTO_INCREMENT列进行重新排列，那么你可以通过删除自增的列，然后重新添加来实现。 不过该操作要非常小心，如果在删除的同时又有新记录添加，有可能会出现数据混乱。操作如下所示：
ALTER TABLE insect DROP id;
ALTER TABLE insect
    -> ADD id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    -> ADD PRIMARY KEY (id);

设置序列的开始值：
一般情况下序列的开始值为1，但如果你需要指定一个开始值100，那我们可以通过以下语句来实现：
CREATE TABLE insect
    -> (
    -> id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    -> PRIMARY KEY (id),
    -> name VARCHAR(30) NOT NULL, 
    -> date DATE NOT NULL,
    -> origin VARCHAR(30) NOT NULL
)engine=innodb auto_increment=100 charset=utf8;
或者你也可以在表创建成功后，通过以下语句来实现：mysql> ALTER TABLE t AUTO_INCREMENT = 100;
```

### MySQL 处理重复数据：
```
防止表中出现重复数据：
如果你想设置表中字段数据不能重复，你可以设置双主键模式来设置数据的唯一性， 如果你设置了双主键，那么那个键的默认值不能为 NULL，可设置为 NOT NULL。如下所示：
CREATE TABLE person_tbl
(
   first_name CHAR(20) NOT NULL,
   last_name CHAR(20) NOT NULL,
   sex CHAR(10),
   PRIMARY KEY (last_name, first_name)
);
INSERT IGNORE INTO 与 INSERT INTO 的区别就是 INSERT IGNORE INTO 会忽略数据库中已经存在的数据，如果数据库没有数据，就插入新的数据，如果有数据的话就跳过这条数据。这样就可以保留数据库中已经存在数据，达到在间隙中插入数据的目的。以下实例使用了 INSERT IGNORE INTO，执行后不会出错，也不会向数据表中插入重复数据：
INSERT IGNORE INTO person_tbl (last_name, first_name)
    -> VALUES( 'Jay', 'Thomas');
Query OK, 1 row affected (0.00 sec)
INSERT IGNORE INTO person_tbl (last_name, first_name)
    -> VALUES( 'Jay', 'Thomas');
Query OK, 0 rows affected (0.00 sec)
另一种设置数据的唯一性方法是添加一个 UNIQUE 索引，如下所示：
CREATE TABLE person_tbl
(
   first_name CHAR(20) NOT NULL,
   last_name CHAR(20) NOT NULL,
   sex CHAR(10),
   UNIQUE (last_name, first_name)
);

统计重复数据：
以下我们将统计表中 first_name 和 last_name的重复记录数：
SELECT COUNT(*) as repetitions, last_name, first_name
    -> FROM person_tbl
    -> GROUP BY last_name, first_name
    -> HAVING repetitions > 1;
以上查询语句将返回 person_tbl 表中重复的记录数。 一般情况下，查询重复的值，请执行以下操作：
确定哪一列包含的值可能会重复。
在列选择列表使用COUNT(*)列出的那些列。
在GROUP BY子句中列出的列。
HAVING子句设置重复数大于1。

过滤重复数据：
如果你需要读取不重复的数据可以在 SELECT 语句中使用 DISTINCT 关键字来过滤重复数据。
SELECT DISTINCT last_name, first_name
    -> FROM person_tbl;
你也可以使用 GROUP BY 来读取数据表中不重复的数据：
SELECT last_name, first_name
    -> FROM person_tbl
    -> GROUP BY (last_name, first_name);

删除重复数据：
如果你想删除数据表中的重复数据，你可以使用以下的SQL语句：
CREATE TABLE tmp SELECT last_name, first_name, sex FROM person_tbl  GROUP BY (last_name, first_name, sex);
DROP TABLE person_tbl;
ALTER TABLE tmp RENAME TO person_tbl;
当然你也可以在数据表中添加 INDEX（索引） 和 PRIMAY KEY（主键）这种简单的方法来删除表中的重复记录。方法如下：
mysql> ALTER IGNORE TABLE person_tbl
    -> ADD PRIMARY KEY (last_name, first_name);
```

### MySQL 及 SQL 注入：
```
// 设定$name 中插入了我们不需要的SQL语句
$name = "Qadir'; DELETE FROM users;";
 mysqli_query($conn, "SELECT * FROM users WHERE name='{$name}'");
以上的注入语句中，我们没有对 $name 的变量进行过滤，$name 中插入了我们不需要的SQL语句，将删除 users 表中的所有数据。

防止SQL注入，我们需要注意以下几个要点：
1.永远不要信任用户的输入。对用户的输入进行校验，可以通过正则表达式，或限制长度；对单引号和 双"-"进行转换等。
2.永远不要使用动态拼装sql，可以使用参数化的sql或者直接使用存储过程进行数据查询存取。
3.永远不要使用管理员权限的数据库连接，为每个应用使用单独的权限有限的数据库连接。
4.不要把机密信息直接存放，加密或者hash掉密码和敏感的信息。
5.应用的异常信息应该给出尽可能少的提示，最好使用自定义的错误信息对原始错误信息进行包装
6.sql注入的检测方法一般采取辅助软件或网站平台来检测，软件一般采用sql注入检测工具jsky，网站平台就有亿思网站安全平台检测工具。MDCSOFT SCAN等。采用MDCSOFT-IPS可以有效的防御SQL注入，XSS攻击等。
```

### MySQL 导出数据：
```
使用 SELECT ... INTO OUTFILE 语句导出数据，以下实例中我们将数据表 runoob_tbl 数据导出到 /tmp/runoob.txt 文件中:
SELECT * FROM runoob_tbl 
    -> INTO OUTFILE '/tmp/runoob.txt';

SELECT ... INTO OUTFILE 语句有以下属性:
LOAD DATA INFILE是SELECT ... INTO OUTFILE的逆操作，SELECT句法。为了将一个数据库的数据写入一个文件，使用SELECT ... INTO OUTFILE，为了将文件读回数据库，使用LOAD DATA INFILE。
SELECT...INTO OUTFILE 'file_name'形式的SELECT可以把被选择的行写入一个文件中。该文件被创建到服务器主机上，因此您必须拥有FILE权限，才能使用此语法。
输出不能是一个已存在的文件。防止文件数据被篡改。
你需要有一个登陆服务器的账号来检索文件。否则 SELECT ... INTO OUTFILE 不会起任何作用。
在UNIX中，该文件被创建后是可读的，权限由MySQL服务器所拥有。这意味着，虽然你就可以读取该文件，但可能无法将其删除。

导出表作为原始数据：
mysqldump 是 mysql 用于转存储数据库的实用程序。它主要产生一个 SQL 脚本，其中包含从头重新创建数据库所必需的命令 CREATE TABLE INSERT 等。使用 mysqldump 导出数据需要使用 --tab 选项来指定导出文件指定的目录，该目标必须是可写的。以下实例将数据表 runoob_tbl 导出到 /tmp 目录中：
$ mysqldump -u root -p --no-create-info \
            --tab=/tmp RUNOOB runoob_tbl
password ******

导出 SQL 格式的数据：
导出 SQL 格式的数据到指定文件，如下所示：
$ mysqldump -u root -p RUNOOB runoob_tbl > dump.txt
password ******
如果你需要导出整个数据库的数据，可以使用以下命令：
$ mysqldump -u root -p RUNOOB > database_dump.txt
password ******
如果需要备份所有数据库，可以使用以下命令：
$ mysqldump -u root -p --all-databases > database_dump.txt
password ******

将数据表及数据库拷贝至其他主机：
如果你需要将备份的数据库导入到MySQL服务器中，可以使用以下命令，使用以下命令你需要确认数据库已经创建：
$ mysql -u root -p database_name < dump.txt
password *****
你也可以使用以下命令将导出的数据直接导入到远程的服务器上，但请确保两台服务器是相通的，是可以相互访问的：
$ mysqldump -u root -p database_name \
       | mysql -h other-host.com database_name
以上命令中使用了管道来将导出的数据导入到指定的远程主机上。
```

### MySQL 导入数据：
```
1、mysql 命令导入：
使用 mysql 命令导入语法格式为：
mysql -u用户名    -p密码    <  要导入的数据库数据(runoob.sql)
# mysql -uroot -p123456 < runoob.sql

2、source 命令导入：
source 命令导入数据库需要先登录到数库终端：
create database abc;      # 创建数据库
use abc;                  # 使用已创建的数据库 
set names utf8;           # 设置编码

3、使用 LOAD DATA 导入数据：
MySQL 中提供了LOAD DATA INFILE语句来插入数据。 以下实例中将从当前目录中读取文件 dump.txt ，将该文件中的数据插入到当前数据库的 mytbl 表中。
LOAD DATA LOCAL INFILE 'dump.txt' INTO TABLE mytbl;
如果指定LOCAL关键词，则表明从客户主机上按路径读取文件。如果没有指定，则文件在服务器上按路径读取文件。
你能明确地在LOAD DATA语句中指出列值的分隔符和行尾标记，但是默认标记是定位符和换行符。
两个命令的 FIELDS 和 LINES 子句的语法是一样的。两个子句都是可选的，但是如果两个同时被指定，FIELDS 子句必须出现在 LINES 子句之前。
如果用户指定一个 FIELDS 子句，它的子句 （TERMINATED BY、[OPTIONALLY] ENCLOSED BY 和 ESCAPED BY) 也是可选的，不过，用户必须至少指定它们中的一个。
LOAD DATA LOCAL INFILE 'dump.txt' INTO TABLE mytbl
  -> FIELDS TERMINATED BY ':'
  -> LINES TERMINATED BY '\r\n';
LOAD DATA 默认情况下是按照数据文件中列的顺序插入数据的，如果数据文件中的列与插入表中的列不一致，则需要指定列的顺序。如，在数据文件中的列顺序是 a,b,c，但在插入表的列顺序为b,c,a，则数据导入语法如下：
LOAD DATA LOCAL INFILE 'dump.txt' 
    -> INTO TABLE mytbl (b, c, a);

4、使用 mysqlimport 导入数据：
mysqlimport 客户端提供了 LOAD DATA INFILEQL 语句的一个命令行接口。mysqlimport 的大多数选项直接对应 LOAD DATA INFILE 子句。从文件 dump.txt 中将数据导入到 mytbl 数据表中, 可以使用以下命令：
$ mysqlimport -u root -p --local mytbl dump.txt
password *****
mysqlimport 命令可以指定选项来设置指定格式,命令语句格式如下：
$ mysqlimport -u root -p --local --fields-terminated-by=":" \
   --lines-terminated-by="\r\n"  mytbl dump.txt
password *****
mysqlimport 语句中使用 --columns 选项来设置列的顺序：
$ mysqlimport -u root -p --local --columns=b,c,a \
    mytbl dump.txt
password *****
```

### MySQL 运算符：
```
MySQL 主要有以下几种运算符：
算术运算符
比较运算符
逻辑运算符
位运算符

算术运算符：
运算符	        作用
+	        加法
-	        减法
*	        乘法
/ 或 DIV	除法
% 或 MOD	取余

比较运算符：
SELECT 语句中的条件语句经常要使用比较运算符。通过这些比较运算符，可以判断表中的哪些记录是符合条件的。比较结果为真，则返回 1，为假则返回 0，比较结果不确定则返回 NULL。
符号	                描述	                                 备注
=	             等于	
<>, !=	             不等于	
>	             大于	
<	             小于	
<=	             小于等于	
>=	             大于等于	
BETWEEN	             在两值之间	                            >=min&&<=max
NOT BETWEEN	     不在两值之间	
IN	             在集合中	
NOT IN	             不在集合中	
<=>	             严格比较两个NULL值是否相等	            两个操作码均为NULL时，其所得值为1；而当一个操作码为NULL时，其所得值为0
LIKE	             模糊匹配	
REGEXP 或 RLIKE	     正则式匹配	
IS NULL	             为空	
IS NOT NULL	     不为空	

逻辑运算符：
逻辑运算符用来判断表达式的真假。如果表达式是真，结果返回 1。如果表达式是假，结果返回 0。
运算符号	        作用
NOT 或 !	逻辑非
AND	        逻辑与
OR	        逻辑或
XOR	        逻辑异或

位运算符：
位运算符是在二进制数上进行计算的运算符。位运算会先将操作数变成二进制数，进行位运算。然后再将计算结果从二进制数变回十进制数。
运算符号	       作用
&	      按位与
|	      按位或
^	      按位异或
!	      取反
<<	      左移
>>	      右移
```