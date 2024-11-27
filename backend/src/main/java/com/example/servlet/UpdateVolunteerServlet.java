package com.example.servlet;

import com.example.dao.VolunteerDao;
import com.example.dao.VolunteerDaoImpl;
import com.example.entity.Volunteer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api/volunteers/update")
public class UpdateVolunteerServlet extends HttpServlet {
    private VolunteerDao volunteerDao;

    @Override
    public void init(){
        volunteerDao = new VolunteerDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Extract parameters from the request
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            // Create a new Volunteer object
            Volunteer volunteer = new Volunteer();
            volunteer.setId(id);
            volunteer.setName(name);
            volunteer.setEmail(email);
            volunteer.setPhone(phone);

            // Update volunteer in the database
            volunteerDao.updateVolunteer(volunteer);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Volunteer updated successfully\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid input\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to update volunteer\"}");
        }
    }
}
