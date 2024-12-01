package com.example.dao;

import com.example.entity.Admin;
import com.example.util.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class AdminDaoImpl implements AdminDao {
    // 创建一个 Logger 实例
    private static final Logger logger = LogManager.getLogger(AdminDaoImpl.class);

    @Override
    public boolean register(Admin admin) {
        String sql = "INSERT INTO Admin (username, password) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            // 使用 logger.error 记录 SQL 异常
            logger.error("SQL Error during registration for username: {}", admin.getUsername(), e);
            return false;
        }
    }
    @Override
    public Admin login(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            logger.error("SQL Error during login for username: {}", username, e);
        }
        return null;
    }
    @Override
    public boolean userExists(String username) {
        String querySql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            logger.error("Error checking if admin user exists in the database", e);
        }
        return false;
    }
}
