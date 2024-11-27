-- 使用数据库
USE volunteer;
DROP TABLE IF EXISTS Admin;


CREATE TABLE Admin (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- 插入数据
INSERT INTO Admin (username, password)
VALUES
    ('zhangsan@admin.com', '13800'),
    ('lisi@admin.com', '13900');
