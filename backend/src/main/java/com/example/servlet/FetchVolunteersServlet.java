package com.example.servlet;

import com.example.dao.VolunteerDao;
import com.example.dao.VolunteerDaoImpl;
import com.example.entity.Volunteer;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/api/volunteers")
public class FetchVolunteersServlet extends HttpServlet {
    private VolunteerDao volunteerDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        volunteerDao = new VolunteerDaoImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            List<Volunteer> volunteers = volunteerDao.getAllVolunteers();
            response.getWriter().write(objectMapper.writeValueAsString(volunteers));
        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to retrieve volunteers\"}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
