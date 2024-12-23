package com.example.dao;

import com.example.entity.Event;

import java.util.List;

public interface EventDao {
    void addEvent(Event event);
    List<Event> getAllEvents();
    Event getEventByTitle(String title);
    void deleteEvent(String title);

    void updateEvent(Event event);
}
