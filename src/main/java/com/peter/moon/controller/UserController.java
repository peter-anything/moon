package com.peter.moon.controller;

import com.peter.moon.common.ResponseBean;
import com.peter.moon.d2r.flows.*;
import com.peter.moon.entity.User;
import com.peter.moon.factory.SqlConnectionFactory;
import com.peter.moon.mapper.UserMapper;
import com.peter.moon.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(SqlConnectionFactory.class);

    @RequestMapping(value = "/detail/{userId}", method = RequestMethod.GET)
    public ResponseBean<User> detail(@PathVariable Integer userId) throws Exception {
        User user = userService.getUserMapperByApplicationContext().selectById(userId);
        DBProcessor dbFlow = new DBProcessor();
//        dbFlow.next = new ThreeTupleFlow();

        int len = 0;
//        DBRow rowList = dbFlow.process();
//        CSVProcessor csvFlow = new CSVProcessor();
//        CSVRow csvRowList = csvFlow.process();

        for (RowData rowData: dbFlow.process()) {
            String[] result = (String[]) rowData.getData();
            len++;

            logger.info(StringUtils.join(result,","));
        }
        logger.error(String.valueOf(len));
        return new ResponseBean<User>("", "", user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseBean<List<User>> all() {
        List<User> needCreateUsers = new ArrayList<>();
        User u = new User();
        u.setCnName("王龙");
        u.setEmail("wanglong@datagrand.com");
        u.setUsername("wanglong");
        needCreateUsers.add(u);

        User u1 = new User();
        u1.setCnName("王龙1");
        u1.setEmail("wanglong1@datagrand.com");
        u1.setUsername("wanglong1");
        needCreateUsers.add(u1);
        userService.doNothing(needCreateUsers);
        List<User> users = userMapper.selectList(null);
        return new ResponseBean<List<User>>("", "", users);
    }
}
