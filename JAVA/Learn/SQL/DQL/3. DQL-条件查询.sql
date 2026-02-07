--  =================== DQL: 条件查询 ======================
-- 1. 查询 姓名 为 柴进 的员工
SELECT * FROM emp WHERE name='柴进';

-- 2. 查询 薪资小于等于5000 的员工信息
SELECT * FROM emp WHERE salary<=5000;

-- 3. 查询 没有分配职位 的员工信息
SELECT * FROM emp WHERE job is NULL;

-- 4. 查询 有职位 的员工信息
SELECT * FROM emp WHERE job is not null;

-- 5. 查询 密码不等于 '123456' 的员工信息
SELECT * FROM emp where password !='132456';

-- 6. 查询 入职日期 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间的员工信息
SELECT * from emp where entry_date between '2000-01-01' and '2010-01-01';

-- 7. 查询 入职时间 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间 且 性别为女 的员工信息
SELECT * from emp where (entry_date between '2000-01-01' and '2010-01-01') && gender=2;


-- 8. 查询 职位是 2 (讲师), 3 (学工主管), 4 (教研主管) 的员工信息
SELECT * FROM emp WHERE job in (2,3,4);
SELECT * FROM emp WHERE job=2 || job=3 || job=4; 

-- 9. 查询 姓名 为两个字的员工信息( _ | % ) 
SELECT * FROM emp WHERE name like '__';

-- 10. 查询 姓 '李' 的员工信息
SELECT * FROM emp WHERE name like '李%';


-- 11. 查询 姓名中包含 '二' 的员工信息

SELECT * FROM emp WHERE name like '%二%';