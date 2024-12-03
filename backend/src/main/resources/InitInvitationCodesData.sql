-- 删除邀请码表（如果存在）
DROP TABLE IF EXISTS invitation_codes;

-- 创建邀请码表
CREATE TABLE invitation_codes (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  code VARCHAR(50) NOT NULL UNIQUE,
                                  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  used BOOLEAN DEFAULT FALSE,
                                  user_id INT,
                                  FOREIGN KEY (user_id) REFERENCES admin(id) ON DELETE SET NULL
);

-- 插入测试数据
INSERT INTO invitation_codes (code, used, user_id) VALUES
                                                       ('ABC123', FALSE, NULL),
                                                       ('XYZ789', FALSE, NULL),
                                                       ('LMN456', FALSE, NULL),
                                                       ('QRS012', FALSE, NULL),
                                                       ('TUV345', FALSE, NULL);
