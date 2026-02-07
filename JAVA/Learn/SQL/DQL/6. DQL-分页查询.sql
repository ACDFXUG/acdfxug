--  =================== 分页查询 ======================
-- 1. 从起始索引0开始查询员工数据, 每页展示5条记录
SELECT * FROM emp LIMIT 0,5;

-- 2. 查询 第1页 员工数据, 每页展示5条记录
SELECT * FROM emp LIMIT 5;

-- 3. 查询 第2页 员工数据, 每页展示5条记录
SELECT * FROM emp LIMIT 5,5;

-- 4. 查询 第3页 员工数据, 每页展示5条记录

SELECT * FROM emp LIMIT 10,5;
