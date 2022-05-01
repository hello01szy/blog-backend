package com.blog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "locked")
    private boolean locked;
    @Column(name = "expired")
    private boolean expired;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "description")
    private String description;
}
