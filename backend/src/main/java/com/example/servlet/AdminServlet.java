package com.example.servlet;

import com.example.dao.AdminDao;
import com.example.dao.AdminDaoImpl;
import com.example.dao.InvitationCodeDao;
import com.example.dao.InvitationCodeDaoImpl;
import com.example.entity.Admin;

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
        if ("register".equals(action)) {
            register(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String invitationCode = request.getParameter("invitationCode");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        if (username == null || password == null || invitationCode == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }
        System.out.println(invitationCode);
        if(!invitationCodeDao.isInvitationCodeExists(invitationCode)) {
            response.getWriter().write("{\"success\": false, \"message\": \"邀请码不正确！\"}");
            return;
        }

        // 检查用户是否已存在
        if (adminDAO.userExists(username)) {
            response.getWriter().write("{\"success\": false, \"message\": \"用户已存在！\"}");
            return;
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        boolean success = adminDAO.register(admin);
        String jsonResponse = success ? "{\"success\": true, \"message\": \"Registration successful\"}" : "{\"success\": false, \"message\": \"Registration failed\"}";
        invitationCodeDao.setInvitationCodeUsed(invitationCode);
        invitationCodeDao.addInvitationCode(
                invitationCodeDao.generateInvitationCode(),
                adminDAO.getAdminIdByName(username)
        );
        response.getWriter().write(jsonResponse);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (username == null || password == null) {
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }

        Admin admin = adminDAO.login(username, password);

        if (admin == null) {
            // 检查用户名是否存在
            if (!adminDAO.userExists(username)) {
                response.getWriter().write("{\"success\": false, \"message\": \"用户不存在！\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"密码不正确！\"}");
            }
            return;
        }
        session.setAttribute("username", username);

        String jsonResponse = "{\"success\": true, \"message\": \"Login successful\"}";
        response.getWriter().write(jsonResponse);
    }

}
