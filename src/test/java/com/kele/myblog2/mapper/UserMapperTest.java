package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/11
 * @Content:
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void fun(){
        User user = userMapper.findall();
        System.out.println(user.toString());

    }
}