package com.blog.entity;

import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Setter
public class BlogUser implements UserDetails {
    private  String password;
    private  String username;
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  Collection<? extends GrantedAuthority> authorities;

    public BlogUser(String password, String username,
                          boolean isAccountNonExpired,
                          boolean isAccountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (this.isAccountNonLocked) {
            return true;
        }
        return false;
    }
}
