package com.peter.moon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("auth_user")
public class User {
    private Integer id;

    private String username;

    private String password;

    private String cnName;

    private String email;

    private String phoneNumber;

    private String department;

    private Boolean enabled;

    private boolean isSuperAdmin;

    private Date lastPasswordResetDate;

    private String defaultGraph;

    private Date createTime;

    private Date lastUpdateTime;
}
