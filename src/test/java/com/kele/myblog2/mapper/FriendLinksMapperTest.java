package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.FriendLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
@SpringBootTest
class FriendLinksMapperTest {

    @Autowired
    private FriendLinksMapper linksMapper;

    @Test
    void fun1(){

        List<FriendLink> allLinks = linksMapper.getAllLinks();

        System.out.println(allLinks);
    }

}