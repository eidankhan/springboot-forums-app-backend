package com.sprinboot.jwt.app.service;

import com.sprinboot.jwt.app.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User finByUsername(String username);

}
