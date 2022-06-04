package com.blog.handler;

import com.blog.consistant.NetStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticatitonFailHander implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 用户账号被锁
        if (exception instanceof LockedException) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = new HashMap<>();
            data.put("code", NetStatus.LOCKED.getCode());
            data.put("msg", NetStatus.LOCKED.getMsg());
            response.getWriter().write(mapper.writeValueAsString(data));
        } else if (exception instanceof AccountExpiredException) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = new HashMap<>();
            data.put("code", NetStatus.EXPIRED.getCode());
            data.put("msg", NetStatus.EXPIRED.getMsg());
            response.getWriter().write(mapper.writeValueAsString(data));
        } else if (exception instanceof BadCredentialsException) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = new HashMap<>();
            data.put("code", NetStatus.ERROR_ACCOUNT.getCode());
            data.put("msg", NetStatus.ERROR_ACCOUNT.getMsg());
            response.getWriter().write(mapper.writeValueAsString(data));
        }
    }
}
