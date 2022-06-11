package com.blog.handler;

import com.blog.utils.JwtUtils;
import com.blog.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class WebTokenLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getHeader("Authentication");
        // 使当前token失效
        List<String> jwtTokens = jwtUtils.getValidTokens();
        jwtTokens.remove(token);
        Response response1 = new Response("200", "已退出登录", null);
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(objectMapper.writeValueAsString(response1));
    }
}
