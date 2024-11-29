package com.example.servlet;

import com.example.dao.AdminDao;
import com.example.dao.AdminDaoImpl;
import com.example.entity.Admin;
import com.example.util.SecretKeyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/api/admin")
public class AdminServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AdminServlet.class);

    private final AdminDao adminDAO = new AdminDaoImpl();
    // 定义密钥
    private static final String SECRET_KEY =  "eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJzdWIiOiAidXNlcjEiLCAibmFtZSI6ICJKb2huIERvZSJ9.b99b7b6abf2da6d8468f043d474d56e77f8cfa7d7cc2ef57d3304b0131f575dd";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if ("register".equals(action)) {
                register(request, response);
            } else if ("login".equals(action)) {
                login(request, response);
            }
        } catch (Exception e) {
            logger.error("Internal server error", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Internal server error\"}");
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            writeResponse(response, false, "Missing required fields", null);
            return;
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        boolean success = adminDAO.register(admin);

        if (success) {
            // 生成 JWT Token
            String jwt = generateJwt(username);
            writeResponse(response, true, "Registration successful", jwt);
        } else {
            writeResponse(response, false, "Registration failed");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            writeResponse(response, false, "Missing required fields");
            return;
        }

        Admin admin = adminDAO.login(username, password);
        if (admin != null) {
            String jwt = generateJwt(username);
            writeResponse(response, true, "Login successful", jwt);
        } else {
            writeResponse(response, false, "Login failed");
        }
    }

    private void writeResponse(HttpServletResponse response, boolean success, String message) throws IOException {
        writeResponse(response, success, message, null);
    }

    private void writeResponse(HttpServletResponse response, boolean success, String message, String token) throws IOException {
        String jsonResponse = String.format("{\"success\": %b, \"message\": \"%s\"%s}", success, message,
                token != null ? ", \"token\": \"" + token + "\"" : "");
        response.getWriter().write(jsonResponse);
    }

    public static String generateJwt(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小时有效期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

}
