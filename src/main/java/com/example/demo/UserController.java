package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService service;

    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(@RequestParam(value = "userName") String userName,
                 @RequestParam(value = "pwd") String pwd) {
        return userName + "登陆成功";
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    List<Map<String, Object>> getUserName() {
        return service.getUserName();
    }
}