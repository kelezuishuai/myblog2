package com.kele.myblog2.service.impl;

import com.kele.myblog2.entity.User;
import com.kele.myblog2.mapper.UserMapper;
import com.kele.myblog2.service.UserService;
import com.kele.myblog2.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/11
 * @Content:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, password);
        return user;
    }
}
