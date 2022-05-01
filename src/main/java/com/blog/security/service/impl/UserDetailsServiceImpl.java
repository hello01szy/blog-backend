package com.blog.security.service.impl;

import com.blog.dao.UserDao;
import com.blog.entity.BlogUser;
import com.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDao.findUserByUsername(username);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("can't find the user");
        }
        User user = users.get(0);
        return this.createUserDetails(user, null);
    }

    /**
     * 根据用户信息创建一个userDetais对象
     * @param user 根据用户名查询出的user对象
     * @param authorities 该用户对应的角色
     * @return
     */
    private UserDetails createUserDetails(User user, List<String> authorities) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = null;
        if (!CollectionUtils.isEmpty(authorities)) {
            authorities.forEach(item -> {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(item));
            });
        }
        return new BlogUser(user.getPassword(), user.getNickname(), user.isExpired(),
                user.isLocked(), null);
    }
}
