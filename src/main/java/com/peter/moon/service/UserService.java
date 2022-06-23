package com.peter.moon.service;

import com.peter.moon.entity.User;
import com.peter.moon.mapper.UserMapper;

import java.util.List;

public interface UserService {
    void bulkCreateUsers(List<User> users);
    void doNothing(List<User> users);
    UserMapper getUserMapperByApplicationContext();
}
