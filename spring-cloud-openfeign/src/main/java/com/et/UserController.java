package com.et;

import com.et.model.User;
import com.et.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    @GetMapping("/user/{id}")
    public User user(@PathVariable Long id, HttpServletRequest request) {
        // get username and password from header
        String userHeader = request.getHeader("user");
        String passwordHeader = request.getHeader("password");

        // print params
        System.out.println("User Header: " + userHeader);
        System.out.println("Password Header: " + passwordHeader);
        User  user =   new User();
        user.setId(id);
        user.setEmail("xxx@xx.com");
        user.setName("test");
        return user;
    }
}