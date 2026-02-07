--  =================== DQL: 分组查询 ======================
-- 聚合函数 不计算null值
-- count,max,min,avg,sum
-- 1. 统计该企业员工数量
SELECT count(id) FROM emp;
SELECT count(*) FROM emp;

-- 2. 统计该企业员工的平均薪资
SELECT AVG(salary) FROM emp;

-- 3. 统计该企业员工的最低薪资
SELECT min(salary) FROM emp;
 
-- 4. 统计该企业员工的最高薪资
SELECT max(salary) FROM emp;

-- 5. 统计该企业每月要给员工发放的薪资总额(薪资之和)

SELECT sum(salary) FROM emp;



-- 分组
-- 1. 根据性别分组 , 统计男性和女性员工的数量
SELECT gender,count(*) FROM emp GROUP BY gender;

-- 2. 先查询入职时间在 '2015-01-01' (包含) 以前的员工 , 并对结果根据职位分组 , 获取员工数量大于等于2的职位
SELECT job,COUNT(*) FROM emp where entry_date <= '2015-01-01' GROUP BY job HAVING count(*) >= 2;