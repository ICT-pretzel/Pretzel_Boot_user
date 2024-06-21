package com.ict.pretzel.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.vo.UserVO;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserVO user = userMapper.login(user_id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + user_id);
        }
        return new User(user.getUser_id(), user.getPwd(), new ArrayList<>());
    }
}
