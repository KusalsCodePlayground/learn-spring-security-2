package com.example.test.service.impl;

import com.example.test.dao.UserDAO;
import com.example.test.entity.impl.User;
import com.example.test.entity.impl.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService  implements UserDetailsService {
    @Autowired
    private UserDAO userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User fetchedUser = userDao.findByUsername(username);
        if(fetchedUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(fetchedUser);
    }
}
