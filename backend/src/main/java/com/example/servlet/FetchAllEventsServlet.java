package com.example.servlet;

import com.example.dao.EventDao;
import com.example.dao.EventDaoImpl;
import com.example.dto.EventDTO;
import com.example.entity.Event;
import com.example.util.JsonUtil;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/events")
public class FetchAllEventsServlet extends HttpServlet {
    private EventDao eventDao;

    @Override
    public void init() {
        eventDao = new EventDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Event> events = eventDao.getAllEvents();
        List<EventDTO> eventDTOs = events.stream()
                .map(EventDTO::new)
                .collect(Collectors.toList());

        try (PrintWriter out = response.getWriter()) {
            out.write(JsonUtil.toJson(eventDTOs));
        }
    }
}
