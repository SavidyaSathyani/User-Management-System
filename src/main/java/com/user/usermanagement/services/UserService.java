package com.user.usermanagement.services;

import com.user.usermanagement.models.User;

public interface UserService {
    public User getByEmail(String email);
    public  String add(User user);
}
