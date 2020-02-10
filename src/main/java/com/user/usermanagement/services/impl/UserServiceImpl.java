package com.user.usermanagement.services.impl;

import com.user.usermanagement.models.User;
import com.user.usermanagement.repositories.UserRepository;
import com.user.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> getByEmail(String email) {
        try{
            User userFound = userRepository.findByEmail(email);

            if(userFound != null) {
                return new ResponseEntity<>(userFound, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        try{
            return new ResponseEntity<>(userRepository.create(user), HttpStatus.CREATED);
        } catch(Exception ex) {
            System.err.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
