package com.example.servlet;

import com.example.dao.InvitationCodeDao;
import com.example.dao.InvitationCodeDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/invitationCodes")
public class FetchInvitationCodesServlet extends HttpServlet {
    private InvitationCodeDao invitationCodeDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        invitationCodeDao = new InvitationCodeDaoImpl();
        objectMapper = new ObjectMapper();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<String> invitationCodes = invitationCodeDao.getAllInvitationCodes();
        response.getWriter().write(objectMapper.writeValueAsString(invitationCodes));
    }
}
