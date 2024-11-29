package com.example.servlet;

import com.example.dao.AdminDao;
import com.example.dao.AdminDaoImpl;
import com.example.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/admin")
public class AdminServlet extends HttpServlet {

    private final AdminDao adminDAO = new AdminDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            register(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        boolean success = adminDAO.register(admin);
        String jsonResponse = success ? "{\"success\": true, \"message\": \"Registration successful\"}" : "{\"success\": false, \"message\": \"Registration failed\"}";

        response.getWriter().write(jsonResponse);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }

        Admin admin = adminDAO.login(username, password);
        String jsonResponse = admin != null ? "{\"success\": true, \"message\": \"Login successful\"}" : "{\"success\": false, \"message\": \"Login failed\"}";

        response.getWriter().write(jsonResponse);
    }

}
