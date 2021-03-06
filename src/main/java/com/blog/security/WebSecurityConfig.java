package com.blog.security;

import com.blog.filter.WebTokenLoginFIlter;
import com.blog.filter.WebTokenVerifyFilter;
import com.blog.handler.CustomAuthenticationSuccessHandler;
import com.blog.handler.CustomAuthenticatitonFailHander;
import com.blog.handler.WebTokenLogoutSuccessHandler;
import com.blog.security.service.impl.UserDetailsServiceImpl;
import com.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    @Autowired
    WebTokenLogoutSuccessHandler webTokenLogoutSuccessHandler;
    @Autowired
    JwtUtils jwtUtils;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register/**").permitAll()
                .antMatchers(
                        "/uploadImg/**",
                        "/uploadFile/**",
                        "/getArticleById/**",
                        "/getAllArticles/**")
                .permitAll()
                .anyRequest().authenticated();
        http.formLogin().disable();
        http.httpBasic().disable();
        http.logout().logoutSuccessHandler(webTokenLogoutSuccessHandler).and();
        // ????????????token???????????????????????????????????????session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        // ????????????
        http.cors().configurationSource(corsConfigurationSource());
        // CRSF????????????????????????session ????????????CRSF??????
        http.csrf().disable();
        // ??????????????????
        http.anonymous().disable();
        http.addFilterBefore(getWebTokenLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(tokenVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
        http.userDetailsService(userDetailsService());
    }

    /**
     * ??????????????????security??????????????????????????????????????????????????????????????????
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

    public WebTokenVerifyFilter tokenVerifyFilter() {
        WebTokenVerifyFilter tokenVerifyFilter = new WebTokenVerifyFilter();
        tokenVerifyFilter.setJwtUtils(jwtUtils);
        return tokenVerifyFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "http://192.168.1.19:8080", "http://192.168.43.183:8080"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        // ?????????????????????????????????????????????????????????????????????Authentication?????????
        configuration.setExposedHeaders(Arrays.asList("Authentication"));
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/uploadImg/**");
        web.ignoring().antMatchers("/getArticleById/**", "/getAllArticles/**");
    }
}
