package com.blog.service.impl;

import com.blog.dao.UserBasicDao;
import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.entity.UserBasicInfo;
import com.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserBasicDao userBasicDao;
    @Override
    public UserBasicInfo getUserInfo(String username) {
        List<UserBasicInfo> users = userBasicDao.getBasicInfo(username);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
