--  =================== DQL: 基本查询 ======================
-- 1. 查询指定字段 name,entry_date 并返回
SELECT name,entry_date FROM emp;
/* SELECT emp.name,emp.entry_date FROM emp; */

-- 2. 查询返回所有字段
SELECT * FROM emp;



-- 3. 查询所有员工的 name,entry_date, 并起别名(姓名、入职日期)
SELECT name AS 姓名,entry_date as 入职日期 FROM emp;


-- 4. 查询已有的员工关联了哪几种职位(不要重复)

SELECT DISTINCT job FROM emp;
