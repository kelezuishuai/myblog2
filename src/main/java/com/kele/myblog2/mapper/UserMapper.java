package com.kele.myblog2.mapper;

import com.kele.myblog2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/11
 * @Content:
 */
@Mapper
@Component
public interface UserMapper {

     User findByUsernameAndPassword(@RequestParam ("username") String username, @RequestParam("password") String password);

     User findall();
}
