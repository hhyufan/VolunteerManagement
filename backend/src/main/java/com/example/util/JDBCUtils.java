package com.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static final Logger logger = LogManager.getLogger();

    private static String url;
    private static String username;
    private static String password;

    static {
        // 读取 db.properties 配置文件
        Properties properties = new Properties();
        try (InputStream input = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                logger.error("Unable to find db.properties");
            }
            properties.load(input);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            // 加载数据库驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException ex) {
            logger.error("Error loading database configuration", ex);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("SQLException occurred while getting connection", e);
        }
        return conn;
    }

    public static void destroy(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("Error closing resources", e);
        }
    }
}
