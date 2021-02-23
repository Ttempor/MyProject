#删除列
ALTER TABLE 表名 DROP COLUMN 列名;

#添加列
ALTER TABLE 表名 ADD COLUMN 列名 类型;

#修改列的类型或约束
ALTER TABLE 表名 MODIFY COLUMN 列名 类型;

#修改列名
ALTER TABLE 表名 CHANGE COLUMN 旧列名 新列名 列类型;

#查看当前库的表
SHOW TABLE;

#表的删除
DROP TABLE IF EXISTS 表名;

#修改表名
ALTER TABLE 表名 RENAME TO 新表名;

#查表结构
DESC eee;
  
#复制表的结构+数据                                      举一反三 仅复制字段
CREATE TABLE 新表名 #                                     CREATE TABLE 新表名
SELECT * FROM 旧表名;# where可复制部分数据                SELECT 字段名,字段名 FROM 旧表名
#                                                         WHERE 0;                                                  
#复制表的结构
CREATE TABLE 新表名 LIKE 旧表名;

#表的创建
CREATE TABLE IF NOT EXISTS 表名(
	列名 列的类型【(长度)约束】,#zerofill填充0，强制为无符号，无符号为unsigned;
	e INT(5) ZEROFILL UNSIGNED,
	列名 列的类型【(长度)约束】
);

#删除库
DROP DATABASE eee;

#修改库，改库名关服务，改文件名。改库字符集
ALTER DATABASE eee CHARACTER SET gbk;

#创建库
CREATE DATABASE IF NOT EXISTS eee;

#truncate table 表名  删表全部内容,影响自增,无返回值
#DELETE FROM 表名 删表全部内容
DELETE FROM beauty WHERE phone LIKE '%9';

#DELETE 要删除的表 from 表名 join 连接的表名 on 连接条件 where 筛选条件;
DELETE  b 
FROM beauty b
INNER JOIN boys bo ON b.`boyfriend_id` = bo.`id`
WHERE bo.`boyName` = 'e';

SELECT boyname FROM boys LIMIT 2;

#UPDATE 表名 join 连接的表名 on 连接条件 set 列名 = 新值 where 筛选条件； 
UPDATE boys bo
RIGHT OUTER JOIN beauty b 
ON bo.`id` = b.`boyfriend_id`
SET b.`boyfriend_id` = 2
WHERE bo.`id` IS NULL;

#UPDATE 表名 set 列名 = 新值 where 筛选条件；
UPDATE boys SET usercp = 850 WHERE id = 8;

#INSERT INTO 表名(要插入的列名) value (插入的值),(插入的值);
插入语句
INSERT INTO beauty(id,NAME,phone)
VALUE(17,'e',12),
(16,'e',122);

#INSERT INTO 表名(要插入的列名) select 行子查询,select 行子查询;
插入语句
INSERT INTO beauty(id,NAME,phone)
SELECT 19,'h',15;
SELECT 'e',15,55;

#INSERT INTO 表名(要插入的列名) set 列名 = 新值，列名 = 新值；
INSERT INTO beauty
SET NAME = 'e',id = 18,phone = 5;

#联合查询
SELECT id,NAME FROM beauty 
UNION ALL#不去重
SELECT id,boyname FROM boys;

#查询语句
SELECT 列名 FROM 表名 JOIN 连接的表名 ON 连接条件 WHERE 筛选条件 GROUP BY 按某列名分组 HAVING 分组条件 
ORDER BY 排序 DESC(降序关键字，升序为ASC) LIMIT 10,20 限制查询;

#子查询
SELECT * FROM employees WHERE (employee_id,salary) = (
	SELECT MIN(employee_id),MAX(salary) 
	FROM employees
);

CREATE DATABASE eee;

USE eee;

DROP TABLE IF  EXISTS eeeee;

CREATE TABLE e(
	e INT PRIMARY KEY,
	e INT NOT NULL,
	e INT UNIQUE,
	e INT DEFAULT 32,
	CONSTRAINT e PRIMARY KEY (e),
	CONSTRAINT e UNIQUE (e),
	CONSTRAINT e FOREIGN KEY (e) REFERENCES 表名(列名),
)


SELECT salary FROM employees WHERE salary = ANY(SELECT MIN(salary) FROM employees);

SELECT MIN(salary) FROM employees;

SELECT last_name,job_id,salary FROM employees WHERE salary = (SELECT MIN(salary) FROM employees);

SELECT city,d.*
FROM departments d
RIGHT OUTER JOIN locations l
ON l.`location_id` = d.`location_id`;
WHERE d.`department_id` IS NULL;

SELECT l.city,d.*
FROM locations l
LEFT JOIN departments d
ON l.location_id = d.location_id;

SELECT d.department_name,e.*
FROM departments d
LEFT OUTER JOIN employees e
ON e.`department_id` = d.`department_id`
WHERE d.department_name IN ('sal','it');
WHERE employee_id IS NOT NULL AND(d.department_name  = 'sal' OR d.department_name = 'it')

SELECT d.department_name,e.*
FROM departments d
RIGHT OUTER JOIN employees e
ON e.`department_id` = d.`department_id`
WHERE d.department_name  = 'sal' OR d.department_name = 'it';

USE girls;

UPDATE boys SET id = 1 WHERE usercp = 800;

UPDATE boys SET id = 5;

SELECT b.*,bo.*
FROM boys bo 
RIGHT OUTER JOIN beauty b
ON b.boyfriend_id = bo.id
WHERE bo.id IS NULL;

SELECT last_name,department_name,job_title
FROM employees e
INNER JOIN departments d ON e.`department_id` = d.`department_id`
INNER JOIN jobs j ON e.`department_id` = j.`job_id`
ORDER BY department_name DESC;
SELECT city,COUNT(*) FROM departments d INNER JOIN locations l ON l.`location_id` = d.`location_id` GROUP BY city 
HAVING COUNT(*) > 3;
SELECT  city,COUNT(*),d.department_name
FROM departments d, locations l
WHERE d.location_id = l.location_id 
GROUP BY d.department_name,city;

SELECT  city,COUNT(*)
FROM departments d, locations l
WHERE d.location_id = l.location_id 
GROUP BY city;

 
SELECT
  boyname,
  NAME,
  phone
FROM
  boys,
  beauty
WHERE boyfriend_id = boys.id;

SELECT department_id,COUNT(*),AVG(salary) FROM employees GROUP BY department_id ORDER BY AVG(salary) DESC;
SELECT MIN(salary),manager_id FROM employees WHERE manager_id IS NOT NULL GROUP BY manager_id HAVING MIN(salary) >= 6000;
SELECT MAX(salary) - MIN(salary) FROM employees;
SELECT job_id ,SUM(salary) a FROM employees WHERE department_id > 50 GROUP BY job_id HAVING SUM(salary) > 5000 ORDER BY a;
SELECT job_id ,SUM(salary) FROM  employees GROUP BY job_id HAVING SUM(salary) = 5000;

SELECT COUNT(department_id) FROM employees WHERE department_id = 90;
SELECT COUNT(CASE department_id WHEN 90 THEN 1 ELSE NULL END) FROM employees;
SELECT SUM(salary),salary,department_id FROM employees;
SELECT salary, CASE  WHEN salary > 5000 THEN 'salary1' WHEN salary <= 5000 THEN 'salary2' ELSE salary END FROM employees;
SELECT STR_TO_DATE('200,5-5','%Y,%c-%d') AS e;
SELECT DATE_FORMAT(hiredate,'%Y年') e FROM employees ORDER BY e DESC;
SELECT YEAR(CURTIME());
SELECT NOW();
SELECT CURTIME();
SELECT CURDATE();
SELECT MOD(1,5);
SELECT CEIL(1.5);
SELECT TRUNCATE(1.54,2);
SELECT FLOOR(1.5);
SELECT ROUND(1.5);
SELECT ROUND(1.66,5);
SELECT LPAD('egg',35,'s');
SELECT RPAD('egggdgdgd',5,'s');
SELECT TRIM('a' FROM 'aa fas eee     aa    ');
SELECT TRIM('     eee         ');
SELECT CONCAT(UPPER(SUBSTR(last_name,1,1)),SUBSTR(last_name,2),'_',first_name) FROM employees;
SELECT * FROM employees WHERE email LIKE '%e%' ORDER BY LENGTH(email) DESC, department_id ASC;
SELECT CONCAT(last_name,first_name) 姓名1, department_id,salary FROM employees 
ORDER BY salary*12 DESC,姓名1;
SELECT * FROM employees WHERE salary LIKE "%5%";
SELECT * FROM employees WHERE salary BETWEEN 1000 AND 5000;
SELECT * FROM employees WHERE salary IN(2500,3000);
SELECT * FROM employees WHERE `commission_pct` IS NULL;
SELECT * FROM employees WHERE `commission_pct` IS NOT NULL;
SELECT * FROM employees WHERE `commission_pct` <=> NULL;
SELECT salary*12 AS 'ee' FROM employees WHERE salary = 2500;
SELECT salary,last_name FROM employees WHERE commission_pct IS NULL 
AND salary < 18000;
SELECT * FROM employees WHERE job_id != 'it' OR salary = 12000;
DESC employees;

SELECT DISTINCT salary FROM employees;
SELECT * FROM employees ORDER BY salary ASC;
SELECT * FROM employees WHERE `department_id` >= 90 ORDER BY `hiredate` DESC;
SELECT salary, LENGTH(first_name) e FROM employees ORDER BY e,salary DESC;

CREATE TABLE e1 (
	e11 INT
)

SHOW INDEX FROM e2;

SHOW INDEX FROM e1;

ALTER TABLE e1 ADD e22 INT UNIQUE;

ALTER TABLE e1 DROP INDEX e22;

ALTER TABLE e1 MODIFY COLUMN e11 INT NOT NULL;

ALTER TABLE e1 DROP PRIMARY KEY;

ALTER TABLE e2 DROP FOREIGN KEY gfgf;

ALTER TABLE e1 DROP INDEX e11

DESC e2;

DROP TABLE IF EXISTS ee;

DELIMITER $
CREATE FUNCTION e(ee FLOAT ,eee FLOAT) RETURNS FLOAT 
BEGIN 
	DECLARE a FLOAT;
	SET a = ee*eee;
	RETURN a;
END $
DELIMITER ;

DROP FUNCTION e;

SELECT e(2.5,3);

SHOW DATABASES;

CREATE TABLE e2 (
	e2 INT,
	CONSTRAINT gfgf FOREIGN KEY (e2) REFERENCES e1(e11) 
);
SELECT * FROM e2;
SELECT * FROM e1;
INSERT INTO e2(e2) VALUE(2);
INSERT INTO e1 VALUE(1);

SELECT @@autocommit;

CREATE TABLE test1 (
	e INT
)

TRUNCATE test1;

CALL ee(10);

SELECT e FROM test1;

DROP PROCEDURE ee;
DELIMITER $
CREATE PROCEDURE ee (IN e INT)
BEGIN
	DECLARE a INT DEFAULT 0;
	l:WHILE  a < e DO
		SET a = a + 1;
		IF MOD(a,2) = 0 THEN ITERATE l;
		END IF;
		INSERT INTO test1 VALUE(a);
	END WHILE l;
END $
DELIMITER ;

DROP PROCEDURE IF EXISTS eee;
DELIMITER $
CREATE PROCEDURE eee (IN e INT)
BEGIN
	DECLARE a INT DEFAULT 0;
	l:REPEAT
		SET a = a + 1;
		CASE a
		WHEN MOD(a,2) = 0 THEN ITERATE l;
		END CASE;
		DELETE FROM test1 WHERE e = a;
	UNTIL a > e
	END REPEAT l;
END $
DELIMITER ;

CALL eee(10);

SELECT  e FROM test1;

