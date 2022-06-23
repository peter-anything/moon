package com.peter.moon.service.impl;

import com.peter.moon.entity.User;
import com.peter.moon.mapper.UserMapper;
import com.peter.moon.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
public class UserServiceImpl implements UserService, ApplicationContextAware, BeanFactoryAware {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    ApplicationContext applicationContext;


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

    @Override
    public UserMapper getUserMapperByApplicationContext() {
        return this.applicationContext.getBean(UserMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
