package com.example.servlet;

import com.example.entity.Event;
import com.example.util.JDBCUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/api/events/select")
public class SelectEventServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");

        if (title == null || title.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Title parameter is required\"}");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM event WHERE title = ?");
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Event event = new Event();
                event.setId(rs.getLong("id"));
                event.setTitle(rs.getString("title"));
                event.setDate(rs.getDate("date"));
                event.setLocation(rs.getString("location"));
                event.setDuration(rs.getLong("duration"));
                event.setContent(rs.getString("content"));
                event.setAttachmentLink(rs.getString("attachmentLink"));
                event.setImageUrl(rs.getString("imageUrl"));

                response.getWriter().write(objectMapper.writeValueAsString(event));
            } else {
                response.getWriter().write("");
            }


        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        } finally {
            JDBCUtils.destroy(conn, pstmt, rs);
        }
    }
}
