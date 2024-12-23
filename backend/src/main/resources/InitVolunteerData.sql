-- 创建数据库并设置字符集为 utf8mb4
CREATE DATABASE IF NOT EXISTS volunteer CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE volunteer;

-- 如果表存在则删除
DROP TABLE IF EXISTS Volunteer;

-- 创建表，使用 utf8mb4 字符集
CREATE TABLE Volunteer (
                           id INT PRIMARY KEY AUTO_INCREMENT,  -- id 自增
                           name VARCHAR(255) NOT NULL,          -- 姓名，非空
                           password VARCHAR(255) ,              -- 密码，管理员不可见
                           email VARCHAR(255) NOT NULL,         -- 邮箱，非空
                           phone VARCHAR(20) NOT NULL           -- 电话，非空
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 插入数据
INSERT INTO Volunteer (name, email, phone)
VALUES
    ('张三', 'zhangsan@example.com', '13800138000'),
    ('李四', 'lisi@example.com', '13900139000'),
    ('王五', 'wangwu@example.com', '13700137000');

