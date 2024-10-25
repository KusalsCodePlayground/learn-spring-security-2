package com.example.test.service;

import com.example.test.dto.UserDTO;
import com.example.test.entity.impl.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User registerUser(UserDTO userDTO);
    String loginUser(UserDTO userDTO);
    String refreshToken(String accessToken);
}
