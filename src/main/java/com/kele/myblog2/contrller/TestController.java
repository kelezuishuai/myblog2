package com.kele.myblog2.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/11
 * @Content:
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String test(){

        return "index";
    }
    @RequestMapping("/login")
    public String login(){

        return "/admin/login";
    }
}
