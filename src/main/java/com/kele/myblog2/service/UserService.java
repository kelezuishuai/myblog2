package com.kele.myblog2.service;

import com.kele.myblog2.entity.User;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/11
 * @Content:
 */
public interface UserService {

    User checkUser(String username, String password);
}
