package com.blog.controller.login;

import com.blog.consistant.NetStatus;
import com.blog.entity.UserBasicInfo;
import com.blog.service.UserInfoService;
import com.blog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUserInfo {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/getUserInfo")
    public Response getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Response res;
        if (authentication == null) {
            res = Response.builder().msg(NetStatus.WITHOUT_LOGIN.getMsg()).code(NetStatus.WITHOUT_LOGIN.getCode()).build();
        } else {
            String username = authentication.getPrincipal().toString().replaceAll("\"", "");
            UserBasicInfo user = userInfoService.getUserInfo(username);
            res = Response.builder().msg("成功获取").code("200").data(user).build();
        }
        return res;
    }
}
