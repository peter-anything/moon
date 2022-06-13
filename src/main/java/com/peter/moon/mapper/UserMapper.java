package com.peter.moon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peter.moon.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void bulkCreateUsers(List<User> users);
}
