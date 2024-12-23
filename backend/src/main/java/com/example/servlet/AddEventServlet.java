package com.example.servlet;

import com.example.dao.*;
import com.example.entity.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/api/events/add")
public class AddEventServlet extends HttpServlet {
    private EventDao eventDao;

    @Override
    public void init() {
        eventDao = new EventDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Extract parameters from the request
            String title = request.getParameter("title");
            String dateStr = request.getParameter("date");
            String location = request.getParameter("location");
            long duration = Long.parseLong(request.getParameter("duration"));
            String content = request.getParameter("content");
            String attachmentLink = request.getParameter("attachmentLink");
            String imageUrl = request.getParameter("imageUrl");

            // Validate input
            if (title == null || dateStr == null || location == null || content == null || attachmentLink == null || imageUrl == null ||
                    title.isEmpty() || dateStr.isEmpty() || location.isEmpty() || content.isEmpty() || attachmentLink.isEmpty() || imageUrl.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing required fields\"}");
                return;
            }

            // Convert the date string to a Date object
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateStr); // Ensure the format matches the input

            // Create a new Event object
            Event event = new Event();
            event.setTitle(title);
            event.setDate(date);
            event.setLocation(location);
            event.setDuration(duration);
            event.setContent(content);
            event.setAttachmentLink(attachmentLink);
            event.setImageUrl(imageUrl);
            System.out.println(event.getTitle());

            // Add event to the database
            eventDao.addEvent(event);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Event added successfully\"}");
        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid date format\"}");
        }
    }
}
