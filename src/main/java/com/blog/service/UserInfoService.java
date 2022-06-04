package com.blog.service;

import com.blog.entity.User;
import com.blog.entity.UserBasicInfo;

public interface UserInfoService {
    UserBasicInfo getUserInfo(String username);
}
