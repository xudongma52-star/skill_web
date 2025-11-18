package com.max.myskills.service;

import com.max.myskills.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * 注册新用户
     * @param user
     * @return 成功后返回一个user对象
     */
    User register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 返回user对象
     */
    User login(String username, String password);
}
