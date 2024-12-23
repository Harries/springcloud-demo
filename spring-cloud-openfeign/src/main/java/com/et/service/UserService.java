package com.et.service;

import com.et.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserServiceClient userServiceClient;

    public User getUser(Long id) {
        return userServiceClient.getUserById(id);
    }
}