package com.example.dao;

import com.example.entity.Volunteer;
import com.example.util.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolunteerDaoImpl implements VolunteerDao {
    private static final Logger logger = LogManager.getLogger(VolunteerDaoImpl.class);

    @Override
    public void addVolunteer(Volunteer volunteer) {
        String insertSql = "INSERT INTO volunteer (name, email, phone) VALUES (?, ?, ?)";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSql)) {

            statement.setString(1, volunteer.getName());
            statement.setString(2, volunteer.getEmail());
            statement.setString(3, volunteer.getPhone());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding volunteer to the database", e);
        }
    }

    @Override
    public void updateVolunteer(Volunteer volunteer) {
        String updateSql = "UPDATE volunteer SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {

            statement.setString(1, volunteer.getName());
            statement.setString(2, volunteer.getEmail());
            statement.setString(3, volunteer.getPhone());
            statement.setInt(4, volunteer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating volunteer in the database", e);
        }
    }

    @Override
    public void deleteVolunteer(int id) {
        String deleteSql = "DELETE FROM volunteer WHERE id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting volunteer from the database", e);
        }
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
        String selectSql = "SELECT * FROM volunteer";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Volunteer volunteer = new Volunteer();
                volunteer.setId(resultSet.getInt("id"));
                volunteer.setName(resultSet.getString("name"));
                volunteer.setEmail(resultSet.getString("email"));
                volunteer.setPhone(resultSet.getString("phone"));
                volunteers.add(volunteer);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving volunteers from the database", e);
        }

        return volunteers;
    }

    @Override
    public Volunteer getVolunteerById(int id) {
        String selectByIdSql = "SELECT * FROM volunteer WHERE id = ?";
        return getVolunteerByQuery(selectByIdSql, id);
    }

    @Override
    public Volunteer getVolunteerByName(String name) {
        String selectByNameSql = "SELECT * FROM volunteer WHERE name = ?";
        return getVolunteerByQuery(selectByNameSql, name);
    }

    private Volunteer getVolunteerByQuery(String query, Object param) {
        Volunteer volunteer = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (param instanceof Integer) {
                statement.setInt(1, (Integer) param);
            } else if (param instanceof String) {
                statement.setString(1, (String) param);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                volunteer = new Volunteer();
                volunteer.setId(resultSet.getInt("id"));
                volunteer.setName(resultSet.getString("name"));
                volunteer.setEmail(resultSet.getString("email"));
                volunteer.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving volunteer from the database", e);
        }

        return volunteer;
    }
}
