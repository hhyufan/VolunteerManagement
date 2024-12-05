package com.example.servlet;

import com.example.dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/invitationCode")
public class FetchInvitationCodeServlet extends HttpServlet {
    private InvitationCodeDao invitationCodeDao;
    private AdminDao adminDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        invitationCodeDao = new InvitationCodeDaoImpl();
        adminDao = new AdminDaoImpl();
        objectMapper = new ObjectMapper();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> invitationCodes = new ArrayList<>();
                response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username parameter is missing");
            return;
        }

        Integer adminId = adminDao.getAdminIdByName(username);
        if (adminId == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Admin not found for username: " + username);
            return;
        }

        invitationCodes = invitationCodeDao.getInvitationCodesByAdmin(adminId);
        response.getWriter().write(objectMapper.writeValueAsString(invitationCodes));
    }
}
