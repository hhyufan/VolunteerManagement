package com.example.dao;

import com.example.entity.Admin;
import com.example.util.JDBCUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class AdminDaoImpl implements AdminDao {
    // 创建一个 Logger 实例
    private static final Logger logger = LogManager.getLogger(AdminDaoImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean register(Admin admin) {
        String sql = "INSERT INTO Admin (username, password, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getEmail());
            pstmt.setString(4, admin.getPhone());
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
                System.out.println(rs.getInt("id") + rs.getString("username") + rs.getString("password"));
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(null);
                admin.setPhone(null);
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
    @Override
    public Integer getAdminIdByName(String username) {
        String querySql = "SELECT id FROM Admin WHERE username = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Error retrieving admin ID for username: {}", username, e);
        }
        return null;
    }
    public String getAdminInfoByName(String username) {
        System.out.println(username);
        String sql = "SELECT * FROM Admin WHERE username = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                admin.setPhone(rs.getString("phone"));
                return objectMapper.writeValueAsString(admin);
            }
        } catch (SQLException | JsonProcessingException e) {
            logger.error("SQL Error during login for username: {}", username, e);
        }
        return null;
    }
}
