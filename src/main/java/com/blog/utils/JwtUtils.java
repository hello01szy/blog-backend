package com.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用来生成jwt以及对前端传至后端的token进行解析
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
@Slf4j
public class JwtUtils {
    // 保质期
    private Long expire;
    // 加密秘文
    private String secret;
    // 加密类型
    private String encryptType;

    private List<String> validTokens = new ArrayList<>();

    /**
     * 生成token
     * @param username 用户名
     * @return 由用户名产生的token
     */
    public String createToken(String username) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + expire);
        // 设置加密类型
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String, Object> header = new HashMap<>(2);
        header.put("type", "jwt");
        header.put("alg", "HS256");
        String token =  JWT.create()
                .withHeader(header)
                .withClaim("userId", username)
                .withExpiresAt(date)
                .sign(algorithm);
        this.validTokens.add(token);
        return token;
    }

    /**
     * 检验token是否正确
     * @param token token
     * @return 用户名
     */
    public boolean verifyToken(String token) {
        if (!this.validTokens.contains(token)) {
            log.info("invalid token");
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("parse token occur exception", e.getMessage());
        }
        return false;
    }

    /**
     * 从jwt token中获取信息
     * @param token 需要被解析的token
     * @return 返回结果
     */
    public String getInfoFromJwt(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = String.valueOf(jwt.getClaim("userId"));
        return username;
    }
}
