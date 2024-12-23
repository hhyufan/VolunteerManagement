package com.example.servlet;

import com.example.dao.EventDao;
import com.example.dao.EventDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/events/delete")
public class DeleteEventServlet extends HttpServlet {
    private EventDao eventDao;

    @Override
    public void init(){
        eventDao = new EventDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("title");
        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Event ID is missing\"}");
            return;
        }

        try {
            String id = idParam;
            eventDao.deleteEvent(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            response.getWriter().write("{\"message\": \"Event deleted successfully\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid event ID\"}");
        }
    }
}
