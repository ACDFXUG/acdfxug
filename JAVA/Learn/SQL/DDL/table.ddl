-- Active: 1770275915648@@127.0.0.1@3306@acdfxug
create table user(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户id', -- 用户id
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    name VARCHAR(10) NOT NULL COMMENT '姓名',
    age TINYINT UNSIGNED COMMENT '年龄',
    gender CHAR(1) DEFAULT '男' COMMENT '性别'
)comment '用户表';

drop table `user`;

--AUTO_INCREMENT 实现自增

SELECT * FROM user;


--  案例:员工表

CREATE table emp(
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '员工id',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(32) DEFAULT '123456' COMMENT '密码',
    name VARCHAR(10) not null COMMENT '姓名',
    gender TINYINT UNSIGNED not null COMMENT '性别,1:男,2:女', 
    phone char(11) not null UNIQUE COMMENT '手机号',
    job TINYINT UNSIGNED COMMENT '职位,1:班主任,2:讲师,3:学工主管,4:教研主管,5:咨询师',
    salary int UNSIGNED COMMENT '薪资',
    entry_date DATE COMMENT '入职时间',
    image VARCHAR(255) COMMENT '头像',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '修改时间'
) COMMENT '员工表';

/* 
show tables;
desc {tableName};
show create table {tableName};
alter table {tableName} add {columnName} {type}(length)
alter table {tableName} modify {columnName} {type}(length)
alter table {tableName} change {oldName} {newName} {type}(length)
alter table {tableName} drop column {columnName}
alter table {tableName} rename to {newTableName}
drop table {tableName};
 */

show TABLES;
DESC emp;
show CREATE TABLE emp;