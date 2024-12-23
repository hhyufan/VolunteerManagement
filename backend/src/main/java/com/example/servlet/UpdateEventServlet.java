package com.example.servlet;

import com.example.dao.EventDao;
import com.example.dao.EventDaoImpl;
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

@WebServlet(urlPatterns = "/api/events/update")
public class UpdateEventServlet extends HttpServlet {
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
            String idStr = request.getParameter("id");
            String title = request.getParameter("title");
            String dateStr = request.getParameter("date");
            String location = request.getParameter("location");
            String durationStr = request.getParameter("duration");
            String content = request.getParameter("content");
            String attachmentLink = request.getParameter("attachmentLink");
            String imageUrl = request.getParameter("imageUrl");

            // Validate input
            if (idStr == null || title == null || dateStr == null || location == null || content == null || attachmentLink == null || imageUrl == null ||
                    idStr.isEmpty() || title.isEmpty() || dateStr.isEmpty() || location.isEmpty() || content.isEmpty() || attachmentLink.isEmpty() || imageUrl.isEmpty() || durationStr.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing required fields\"}");
                return;
            }

            // Convert the date string to a Date object with time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = dateFormat.parse(dateStr);

            // Parse duration
            long duration = Long.parseLong(durationStr);

            // Create a new Event object
            Event event = new Event();
            event.setId(Long.parseLong(idStr)); // Set the ID for the event
            event.setTitle(title);
            event.setDate(date); // Ensure this is a java.util.Date
            event.setLocation(location);
            event.setDuration(duration);
            event.setContent(content);
            event.setAttachmentLink(attachmentLink);
            event.setImageUrl(imageUrl);

            // Update event in the database
            eventDao.updateEvent(event);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Event updated successfully\"}");
        } catch (ParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid date format\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid ID or duration format\"}");
        }
    }
}
