package com.example.test.service.impl;

import com.example.test.dao.UserDAO;
import com.example.test.dto.UserDTO;
import com.example.test.entity.impl.User;
import com.example.test.service.UserService;
import com.example.test.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.test.util.AppUtil.generateUserId;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private Mapping mapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerUser(UserDTO userDTO) {
        userDTO.setId(generateUserId());
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        return userDao.save(mapper.mapToUser(userDTO));
    }

    @Override
    public String loginUser(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );
        if(auth.isAuthenticated()){
            return jwtService.generateToken(userDTO.getUsername());
        }
        return "Login Failed";
    }

    @Override
    public String refreshToken(String accessToken){
        String username = jwtService.extractUsername(accessToken);
        User fetchedUsername = userDao.findByUsername(username);
        if (fetchedUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return jwtService.generateToken(username);
    }
}
