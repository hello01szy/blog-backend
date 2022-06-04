package com.blog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class UserBasicInfo {
    @Id
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "description")
    private String description;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "email")
    private String email;
}
