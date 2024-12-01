package com.example.dao;

import com.example.entity.Admin;

public interface AdminDao {

    boolean register(Admin admin);

    Admin login(String username, String password);

    boolean userExists(String username);
}
