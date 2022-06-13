package com.peter.moon.service;

import com.peter.moon.entity.User;

import java.util.List;

public interface UserService {
    void bulkCreateUsers(List<User> users);
}
