--  =================== 排序查询 ======================
-- 1. 根据入职时间, 对员工进行升序排序
SELECT * FROM emp ORDER BY entry_date asc;

-- 2. 根据入职时间, 对员工进行降序排序
SELECT * FROM emp ORDER BY entry_date desc;

-- 3. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 更新时间 进行降序排序

SELECT * FROM emp ORDER BY entry_date asc, update_time desc;