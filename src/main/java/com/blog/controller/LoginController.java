package com.blog.controller;

import com.blog.dto.LoginParamDto;
import com.blog.dto.RegisterInfoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody LoginParamDto param) {
        return "登录成功";
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterInfoDto registerInfo) {
        return "注册成功";
    }
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello";
    }
}
