package com.example.servlet;

import com.example.dao.VolunteerDao;
import com.example.dao.VolunteerDaoImpl;
import com.example.entity.Volunteer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api/volunteers/add")
public class AddVolunteerServlet extends HttpServlet {
    private VolunteerDao volunteerDao;

    @Override
    public void init() {
        volunteerDao = new VolunteerDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Extract parameters from the request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            // Validate input (optional, but can be added for better reliability)
            if (name == null || email == null || phone == null || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing required fields\"}");
                return;
            }

            // Create a new Volunteer object
            Volunteer volunteer = new Volunteer();
            volunteer.setName(name);
            volunteer.setEmail(email);
            volunteer.setPhone(phone);

            // Add volunteer to the database
            volunteerDao.addVolunteer(volunteer);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Volunteer added successfully\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to add volunteer\"}");
        }
    }
}
