package com.example.dao;

import com.example.util.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventRecordDaoImpl implements EventRecordDao {
    private static final Logger logger = LogManager.getLogger(EventRecordDaoImpl.class);

    @Override
    public void deleteEventRecordByVolunteerId(Long volunteerId) {
        String deleteSql = "DELETE FROM event_record WHERE volunteer_id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setLong(1, volunteerId);
            int rowsAffected = statement.executeUpdate(); // 获取受影响的行数
            logger.info("Deleted {} event records for volunteer ID: {}", rowsAffected, volunteerId);
        } catch (SQLException e) {
            logger.error("Error deleting event records for volunteer ID: {}", volunteerId, e);
        }
    }

    @Override
    public void deleteEventRecordByEventId(Long eventId) {
        String deleteSql = "DELETE FROM event_record WHERE event_id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setLong(1, eventId);
            int rowsAffected = statement.executeUpdate(); // 获取受影响的行数
            logger.info("Deleted {} event records for event ID: {}", rowsAffected, eventId);
        } catch (SQLException e) {
            logger.error("Error deleting event records for event ID: {}", eventId, e);
        }
    }
}
