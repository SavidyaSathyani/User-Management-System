package com.user.usermanagement.controllers;

import com.user.usermanagement.models.User;

import com.user.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody User userRequest) {
        return userService.addUser(userRequest);
    }

    @GetMapping("/{email}")
    public  ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }
}
