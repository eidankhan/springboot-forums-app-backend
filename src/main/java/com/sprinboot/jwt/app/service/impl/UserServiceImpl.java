package com.sprinboot.jwt.app.service.impl;

import com.sprinboot.jwt.app.model.User;
import com.sprinboot.jwt.app.repository.UserRepository;
import com.sprinboot.jwt.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User finByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
