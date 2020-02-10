package com.user.usermanagement.services;

import com.user.usermanagement.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User> getByEmail(String email);
    ResponseEntity<String> addUser(User user);
}
