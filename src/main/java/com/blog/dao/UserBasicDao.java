package com.blog.dao;

import com.blog.entity.UserBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBasicDao extends JpaRepository<UserBasicInfo, Integer> {
    @Query(nativeQuery = true, value = "select nickname, email, description, create_time, avatar from user " +
            "where nickname = :username")
    List<UserBasicInfo> getBasicInfo(@Param("username") String username);
}
