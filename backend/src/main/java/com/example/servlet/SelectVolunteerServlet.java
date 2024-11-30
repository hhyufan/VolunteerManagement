package com.example.servlet;

import com.example.dao.VolunteerDao;
import com.example.dao.VolunteerDaoImpl;
import com.example.entity.Volunteer;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api/volunteers/select")
public class SelectVolunteerServlet extends HttpServlet {

    private VolunteerDao volunteerDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        volunteerDao = new VolunteerDaoImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Name parameter is required\"}");
            return;
        }
        try {
            Volunteer volunteer = volunteerDao.getVolunteerByName(name);
            response.getWriter().write(objectMapper.writeValueAsString(volunteer));

        } catch (IOException e) {
            // Handle other IO exceptions
            throw new RuntimeException(e);
        }
    }
}
