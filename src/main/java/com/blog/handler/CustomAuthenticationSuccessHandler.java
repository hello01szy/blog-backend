package com.blog.handler;

import com.blog.entity.BlogUser;
import com.blog.utils.JwtUtils;
import com.blog.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtUtils jwtUtils;
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 获取当前登录用户
        BlogUser user = (BlogUser) authentication.getPrincipal();
        String token = jwtUtils.createToken(user.getUsername());
        response.setStatus(200);
        response.setHeader("Authentication", token);
        response.setContentType("application/json;charset=UTF-8");
        Response response1 = new Response("200", "认证通过", null);
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(objectMapper.writeValueAsString(response1));
    }
}
