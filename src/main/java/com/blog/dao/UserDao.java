package com.blog.dao;

import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{
    @Query(nativeQuery = true, value = "select * from user where nickname = :username")
    List<User> findUserByUsername(@Param("username") String username);
//    @Query(nativeQuery = true, value = "select * from user")
//    List<String> findAuthoritiesByUsername(@Param("username") String username);
}
