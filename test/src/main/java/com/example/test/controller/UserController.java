package com.example.test.controller;

import com.example.test.dto.UserDTO;
import com.example.test.service.UserService;
import com.example.test.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Mapping mapper;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return mapper.mapToUserDTO(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO);
    }

    @PostMapping("/refresh")
    public String loginUser(@RequestParam String accessToken) {
        try{
            return userService.refreshToken(accessToken);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
