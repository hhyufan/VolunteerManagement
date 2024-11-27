//package com.example.servlet;
//
//import com.example.dao.VolunteerDao;
//import com.example.dao.VolunteerDaoImpl;
//import com.example.entity.Volunteer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/api/volunteers/*", asyncSupported = true)
//public class VolunteerServlet extends HttpServlet {
//
//    private VolunteerDao volunteerDao;
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void init() throws ServletException {
//        volunteerDao = new VolunteerDaoImpl();
//        objectMapper = new ObjectMapper();
//    }
//    @Override
//    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//
//        try {
//            List<Volunteer> volunteers = volunteerDao.getAllVolunteers();
//            response.getWriter().write(objectMapper.writeValueAsString(volunteers));
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\": \"Failed to retrieve volunteers\"}");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//
//        try {
//            Volunteer volunteer = objectMapper.readValue(request.getReader(), Volunteer.class);
//            volunteerDao.addVolunteer(volunteer);
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            response.getWriter().write("{\"message\": \"Volunteer created successfully\"}");
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\": \"Failed to add volunteer\"}");
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//
//        try {
//            Volunteer volunteer = objectMapper.readValue(request.getReader(), Volunteer.class);
//            volunteerDao.updateVolunteer(volunteer);
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write("{\"message\": \"Volunteer updated successfully\"}");
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\": \"Failed to update volunteer\"}");
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//
//        String pathInfo = request.getPathInfo();
//        if (pathInfo == null || pathInfo.equals("/")) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{\"error\": \"Volunteer ID is missing\"}");
//            return;
//        }
//
//        String[] splits = pathInfo.split("/");
//        if (splits.length < 2) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{\"error\": \"Invalid URL format\"}");
//            return;
//        }
//
//        try {
//            int id = Integer.parseInt(splits[1]);
//            volunteerDao.deleteVolunteer(id);
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        } catch (NumberFormatException e) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("{\"error\": \"Invalid volunteer ID\"}");
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\": \"Failed to delete volunteer\"}");
//        }
//    }
//}
