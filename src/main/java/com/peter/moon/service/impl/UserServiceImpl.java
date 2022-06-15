package com.peter.moon.service.impl;

import com.peter.moon.entity.User;
import com.peter.moon.mapper.UserMapper;
import com.peter.moon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void bulkCreateUsers(List<User> users) {
        for (User user: users) {
            user.setPassword(passwordEncoder.encode(user.getPassword() == null ? "123456" : user.getPassword()));
        }
        userMapper.hashCode();
    }


    @Override
    public void doNothing(List<User> users) {
        for (User user: users) {
            user.setPassword(passwordEncoder.encode(user.getPassword() == null ? "123456" : user.getPassword()));
        }
        System.out.println(userMapper.hashCode());
    }
}
