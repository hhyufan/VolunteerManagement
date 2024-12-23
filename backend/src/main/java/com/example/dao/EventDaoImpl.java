package com.example.dao;

import com.example.entity.Event;
import com.example.util.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {
    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);
    public void addEvent(Event event) {
        String insertSql = "INSERT INTO event (title, date, location, duration, content, attachmentLink, imageUrl) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(event.getTitle());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSql)) {

            statement.setString(1, event.getTitle());
            statement.setTimestamp(2, new java.sql.Timestamp(event.getDate().getTime())); // 使用 Timestamp
            statement.setString(3, event.getLocation());
            statement.setLong(4, event.getDuration());
            statement.setString(5, event.getContent());
            statement.setString(6, event.getAttachmentLink());
            statement.setString(7, event.getImageUrl());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding event to the database", e);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String selectSql = "SELECT * FROM event";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong("id"));
                event.setTitle(resultSet.getString("title"));

                Timestamp timestamp = resultSet.getTimestamp("date");
                if (timestamp != null) {
                    event.setDate(timestamp); // 假设 Event 类中有一个 setDate 方法
                }

                event.setLocation(resultSet.getString("location"));
                event.setDuration(resultSet.getLong("duration"));
                event.setContent(resultSet.getString("content"));
                event.setAttachmentLink(resultSet.getString("attachmentLink"));
                event.setImageUrl(resultSet.getString("imageUrl"));
                System.out.println(resultSet.getDate("date").getTime());
                events.add(event);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving events from the database", e);
        }

        return events;
    }

    @Override
    public void deleteEvent(String title) {
        String deleteSql = "DELETE FROM event WHERE title = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting event from the database", e);
        }
    }

    @Override
    public void updateEvent(Event event) {
        String updateSql = "UPDATE event SET title = ?, date = ?, location = ?, duration = ?, content = ?, attachmentLink = ?, imageUrl = ? WHERE id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {

            statement.setString(1, event.getTitle());
            statement.setTimestamp(2, new java.sql.Timestamp(event.getDate().getTime())); // 使用 Timestamp
            statement.setString(3, event.getLocation());
            statement.setLong(4, event.getDuration());
            statement.setString(5, event.getContent());
            statement.setString(6, event.getAttachmentLink());
            statement.setString(7, event.getImageUrl());
            statement.setLong(8, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating event in the database", e);
        }
    }

}
