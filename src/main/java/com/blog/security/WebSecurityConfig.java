package com.blog.security;

import com.blog.filter.WebTokenLoginFIlter;
import com.blog.handler.CustomAuthenticationSuccessHandler;
import com.blog.handler.CustomAuthenticatitonFailHander;
import com.blog.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticatitonFailHander customAuthenticatitonFailHander;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin().disable();
        http.httpBasic().disable();
        // 支持跨域
        http.cors().configurationSource(corsConfigurationSource());
        // CRSF禁用，因为不使用session 可以预防CRSF攻击
        http.csrf().disable();
        http.addFilterBefore(getWebTokenLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.userDetailsService(userDetailsService());
    }

    /**
     * 定义加密器，security框架需要使用该密码加密器对密码进行加密和解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public WebTokenLoginFIlter getWebTokenLoginFilter() throws Exception {
        WebTokenLoginFIlter webTokenLoginFIlter = new WebTokenLoginFIlter();
        webTokenLoginFIlter.setAuthenticationManager(this.authenticationManager());
        webTokenLoginFIlter.setAuthenticationFailureHandler(customAuthenticatitonFailHander);
        webTokenLoginFIlter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        return webTokenLoginFIlter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "http://192.168.1.19:8080"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
