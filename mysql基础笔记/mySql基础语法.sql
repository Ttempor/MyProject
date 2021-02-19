#BEGIN END 中的 WHILE 循环 ---- 先判断后执行
Label:WHILE 循环条件 DO      - 
循环体                       -
END WHILE Label;             -
                             -
#BEGIN END 中的 Repeat 循环 --- 先执行后判断
Label:REPEAT                 -
循环体                       -
UNTIL 循环结束条件           -
END REPEAT Label;            -
                             -
#BEGIN END 中的 Loop 循环 ----- 无限循环
Label:LOOP
循环体
END LOOP Label;

#循环控制
ITERATE Label   类似于continue;
LEAVE Label     类似于break;

#IF结构，只能放在 begin and 中
IF 条件1 THEN 语句1;
ELSEIF 条件2 THEN 语句2;
...
ELSE 语句;
END IF;

#CASE结构作为语句的写法,想要作为语句,只能放在 begin end 中
CASE 变量/表达式/列名
WHEN 值 THEN 要执行的语句;
WHEN 值 THEN 要执行的语句;
...
ELSE 要执行的语句;#必须要写else,否则when都没有的情况下可能会出现Case not found for CASE statement
END CASE;

#CASE结构作为表达式的写法
CASE 变量/表达式/列名
WHEN 值 THEN 返回值1
WHEN 值 THEN 返回值2
..
ELSE 要返回的值  #可以不写else
END

#函数的创建
CREATE FUNCTION 函数名(参数名 参数类型...) RETURNS 返回值类型
BEGIN
	函数体
	RETURN
END

#调用函数
SELECT 函数名(参数列表)

#查看函数信息
SHOW CREATE FUNCTION 函数名;

#删除函数
DROP FUNCTION 函数名;

#更改结束标记
DELIMITER 标记

#存储过程的创建
CREATE PROCEDURE 存储过程名(参数模式 参数名 参数类型...)
BEGIN 
	合法的SQL语句;
END 标记（需要修改分隔符;无效 只有一句话可以省略 BEGIN END ）

#参数列表
参数模式 参数名 参数类型
参数模式：
IN:输入
OUT:返回值
INOUT:既输入，又返回

#调用存储过程
CALL 存储过程名();

#查看存储过程的信息
SHOW CREATE PROCEDURE 存储过程名;

#删除存储过程
DROP PROCEDURE 存储过程名;

#查看自定义变量,局部变量，作用域仅在begin end中第一句话有效。
SELECT 变量名;

#更改自定义变量,局部变量，作用域仅在begin end中第一句话有效。
SET 变量名 = 值;
SET 变量名 := 值;
SELECT @变量名 := 值;
SELECT 列名 INTO 变量名 FROM 表名;

#声明自定义变量，局部变量，作用域仅在begin end中第一句话有效。
DECLARE 变量名 类型;
DECLARE 变量名 类型 DEFAULT 默认值;

#查看自定义变量,用户变量，作用域与会话变量相同.
SELECT @变量名;

#更改自定义变量,用户变量，作用域与会话变量相同.
SET @变量名 = 值;
SET @变量名 := 值;
SELECT @变量名 := 值;
SELECT 列名 INTO @变量名 FROM 表名;

#声明自定义变量,用户变量，作用域与会话变量相同.
SET @变量名 = 值;
SET @变量名 := 值;
SELECT @变量名 := 值;

#设置系统变量
SET SESSION/GLOBAL(可省略默认 SESSION) 变量名 = 新值；
SET @@SESSION/GLOBAL.变量名 = 新值

#查看系统变量  
SHOW SESSION/GLOBAL(可省略默认 SESSION) VARIABLES
SELECT  @@SESSION/GLOBAL.变量名；

#视图的创建                     视图能DML，可同时对原表进行修改，建议DQL
CREATE VIEW 视图名
AS
查询语句

#视图的修改
CREATE OR REPLACE 视图名        ALTER VIEW 视图名
AS                              AS
查询语句                        查询语句

#视图的删除                     #查看视图
DROP VIEW 视图名,视图名;        DESC 视图名;

#事物  
SET autocommit = 0;
START TRANSACTION;
dql dml
SAVEPOINT 保存名;
dql dml
COMMIT;
ROLLBACK;#ROLLBACK 保存名 回滚到保存点   事物中的truncate table会删表，delete from不会删表

#查看当前隔离级别
SELECT @@transaction_isolation;

#设置当前连接隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL 隔离级别;

#设置全局隔离级别
SET GLOBAL TRANSACTION ISOLATION LEVEL 隔离基本;

#列级约束修改
ALTER TABLE 表名 MODIFY COLUMN 列名 约束(主键，唯一，非空，默认) AUTO_INCREMENT;

#添加表级约束 外键
ALTER TABLE 表名 ADD CONSTRAINT 外键名 FOREIGN KEY (列名) REFERENCES 表名(列名)

#添加表级约束 唯一键
ALTER TABLE 表名 ADD CONSTRAINT 唯一键键名 UNIQUE(列名)

#删除表的主键 #删key唯一方式
ALTER TABLE 表名 DROP PRIMARY KEY

#删除表的唯一键 #删key唯一方式
ALTER TABLE 表名 DROP INDEX 唯一键键名

#删除表的外键  #删key唯一方式
ALTER TABLE 表名 DROP FOREIGN KEY 外键键名

#约束  mysql不支持check约束，列举约束不支持外键,表级约束不支持非空，默认
CREATE TABLE IF NOT EXISTS ee(
	id INT NOT NULL,#非空
	id1 INT DEFAULT 11,#默认
	id2 INT PRIMARY KEY AUTO_INCREMENT,#主键 不可非空，自增标识列 可改起始值和步长 只能和key搭配，类型必须为数值型，表中最多有一个。
	id3 INT UNIQUE,#唯一
#	id4 INT REFERENCES,#表名(列名)
	id5 INT CHECK(id5 IN (1,5)),#检查
	id6 INT UNIQUE NOT NULL DEFAULT 1#可有多个约束
)
#表级约束
CREATE TABLE IF NOT EXISTS ee(
	id INT  ,
	id1 INT ,
	id2 INT ,
	id3 INT ,
	id4 INT ,
	id5 INT ,
	CONSTRAINT eee NOT NULL(id),#非空
	CONSTRAINT ee DEFAULT 5,#默认
	CONSTRAINT e CHECK(id5 IN (1,5)),#检查
	CONSTRAINT ee PRIMARY KEY(id),#主键 不可非空 可以组合
	CONSTRAINT eee UNIQUE(id),#唯一键 可以组合
	CONSTRAINT e1 FOREIGN KEY (id) REFERENCES 表名(列名)#外键
)

#查表中所有的索引
SHOW INDEX FROM 表名;

CREATE TABLE IF NOT EXISTS dept1 (
	id INT(7) ZEROFILL, #不够则补零
	NAME VARCHAR(25)
);
ALTER TABLE  girls.dept1 MODIFY COLUMN id INT(5);
DROP TABLE dept1;

#增删改查 DML
#增:
INSERT INTO 表名() VALUE(),();

INSERT INTO 表名() VALUE SELECT(行子查询),SELECT(行子查询);

INSERT INTO 表名() SET 列名 = 新值,列名 = 新值;

#删:
TRUNCATE TABLE 表名;#删自增

DELETE FROM 表名 WHERE 筛选条件;#不删自增

DELETE 要删的表名 FROM 表名 JOIN 连接的表名 ON 连接条件 WHERE 筛选条件;

#改:
UPDATE 表名 SET 列名 = 新值,列名 = 新值 WHERE 筛选条件;

UPDATE 表名 JOIN 连接的表名 ON 连接条件 SET 列名 = 新值,列名 = 新值 WHERE 筛选条件;

#查:
SELECT 列名 FROM 表名 JOIN 连接的表名 ON 连接条件 WHERE 筛选条件 GROUP BY 要按哪个列名分组 HAVING 分组筛选条件
ORDER BY 按哪个列名排序 DESC(降序，升序为asc) LIMIT 5，10;

#联合查询，子查询， IN SOME ANY ALL


#表 DDL   

#查看当前库的表
SHOW TABLE;

#查表结构
DESC 表名;
	
#删除表的某列:
ALTER TABLE 表名 DROP COLUMN 列名;
	
#表增加一列
ALTER TABLE 表名 ADD COLUMN 列名 类型;

#修改表的某列类型
ALTER TABLE 表名 MODIFY COLUMN 列名 类型;
	
#修改表的某列名字
ALTER TABLE 表名 CHANGE COLUMN 旧列名 新列名 类型;
	
#修改表名 
ALTER TABLE 表名 RENAME TO 新表名;


#创建表
CREATE TABLE IF NOT EXISTS 表名(
	列名 类型,
	列名 类型
);
	
#删除表
DROP TABLE IF NOT EXISTS 表名;

#创建库 
CREATE DATABASE IF NOT EXISTS 库名;

#删除库 
DROP DATABASE IF NOT EXISTS 库名;