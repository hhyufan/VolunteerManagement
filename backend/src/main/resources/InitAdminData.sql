-- 使用数据库
USE volunteer;

-- 删除现有的 Admin 表（如果存在）
DROP TABLE IF EXISTS Admin;

-- 创建新的 Admin 表，包括 email 和 phone 字段
CREATE TABLE Admin (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone VARCHAR(20)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 插入新的数据，包括 email 和 phone
INSERT INTO Admin (username, password, email, phone)
VALUES
    ('zhangsan@admin.com', '13800', 'zhangsan@admin.com', '1234567890'),
    ('lisi@admin.com', '13900', 'lisi@admin.com', '0987654321');
