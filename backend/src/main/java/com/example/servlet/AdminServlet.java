package com.example.servlet;

import com.example.dao.AdminDao;
import com.example.dao.AdminDaoImpl;
import com.example.dao.InvitationCodeDao;
import com.example.dao.InvitationCodeDaoImpl;
import com.example.entity.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/admin")
public class AdminServlet extends HttpServlet {

    private final AdminDao adminDAO = new AdminDaoImpl();
    private final InvitationCodeDao invitationCodeDao = new InvitationCodeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if ("register".equals(action)) {
            register(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        } else if ("fetch".equals(action)) {
            fetchInfo(request, response);
        } else {
            response.getWriter().write("{\"error\": \"Invalid action\"}");
        }
    }

    private void fetchInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String username = request.getParameter("username");
        String adminInfo = adminDAO.getAdminInfoByName(username);
        System.out.println(adminInfo);
        if (adminInfo == null) {
            response.getWriter().write("{\"error\": \"Username not exists\"}");
            return;
        }

        // 返回管理员信息
        response.getWriter().write(adminInfo);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String invitationCode = request.getParameter("invitationCode");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        System.out.println("Registering user: " + username);

        if (username == null || password == null || invitationCode == null || phone == null || email == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }

        if (!invitationCodeDao.isInvitationCodeExists(invitationCode)) {
            System.out.println("Invitation code not exists");
            response.getWriter().write("{\"success\": false, \"message\": \"邀请码不正确或已被使用！\"}");
            return;
        }
        System.out.println("Invitation code: " + invitationCode);
        if (adminDAO.userExists(username)) {
            response.getWriter().write("{\"success\": false, \"message\": \"用户已存在！\"}");
            return;
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setPhone(phone);
        admin.setEmail(email);
        boolean success = adminDAO.register(admin);
        String jsonResponse = success ? "{\"success\": true, \"message\": \"Registration successful\"}" : "{\"success\": false, \"message\": \"Registration failed\"}";

        if (success) {
            invitationCodeDao.setInvitationCodeUsed(invitationCode);
            invitationCodeDao.addInvitationCode(
                    invitationCodeDao.generateInvitationCode(),
                    adminDAO.getAdminIdByName(username)
            );
        }

        response.getWriter().write(jsonResponse);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Logging in user: " + username);

        if (username == null || password == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }

        Admin admin = adminDAO.login(username, password);
        if (admin == null) {
            if (!adminDAO.userExists(username)) {
                response.getWriter().write("{\"success\": false, \"message\": \"用户不存在！\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"密码不正确！\"}");
            }
            return;
        }

        String jsonResponse = "{\"success\": true, \"message\": \"Login successful\"}";
        response.getWriter().write(jsonResponse);
    }
}
