package com.user.usermanagement.services.impl;

import com.user.usermanagement.models.User;
import com.user.usermanagement.repositories.UserRepository;
import com.user.usermanagement.services.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        try{
            return userRepository.findByEmail(email);
        } catch (Exception ex) {
            System.out.println(ex);
            return userRepository.findByEmail(email);

        } finally {

        }

    }

    @Override
    public String add(User user) {
        try{
            return userRepository.create(user);
        } catch(Exception ex) {
            System.err.println(ex);
            return userRepository.create(user);
        } finally {

        }
    }
}
