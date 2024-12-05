package com.example.servlet;

import com.example.dao.AdminDao;
import com.example.dao.AdminDaoImpl;
import com.example.dao.InvitationCodeDao;
import com.example.dao.InvitationCodeDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/invitationCode/add")
public class AddInvitationCodeServlet extends HttpServlet {
    private InvitationCodeDao invitationCodeDao;
    private AdminDao adminDAO;
    @Override
    public void init(){
        invitationCodeDao = new InvitationCodeDaoImpl();
        adminDAO = new AdminDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String invitationCode = request.getParameter("invitationCode");
        String username = request.getParameter("username");

        if (username == null || invitationCode == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing required fields\"}");
            return;
        }
        if (invitationCodeDao.isInvitationCodeExists(invitationCode)) {
            response.getWriter().write("{\"error\": \"Invitation Code already exists！\"}");
        }

        invitationCodeDao.addInvitationCode(invitationCode, adminDAO.getAdminIdByName(username));

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("{\"message\": \"invitationCode added successfully\"}");
    }
}
