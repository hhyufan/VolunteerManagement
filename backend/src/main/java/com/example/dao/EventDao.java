package com.example.dao;

import com.example.entity.Event;

import java.util.List;

public interface EventDao {
    public void addEvent(Event event);
    public List<Event> getAllEvents();

    void deleteEvent(String title);

    void updateEvent(Event event);
}
