package com.example.dao;

import com.example.entity.Volunteer;
import java.sql.SQLException;
import java.util.List;

public interface VolunteerDao {
    List<Volunteer> getAllVolunteers() throws SQLException, ClassNotFoundException;
    void addVolunteer(Volunteer volunteer) throws SQLException;

    void updateVolunteer(Volunteer volunteer) throws SQLException;

    void deleteVolunteer(int id) throws SQLException;

    Volunteer getVolunteerById(int id);

    Volunteer getVolunteerByName(String name);
}
