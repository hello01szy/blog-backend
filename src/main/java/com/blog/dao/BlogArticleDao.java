package com.blog.dao;

import com.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogArticleDao extends JpaRepository<BlogArticle, Integer> {
    @Query(nativeQuery = true, value = "select * from blog where id = :articleId")
    List<BlogArticle> findArticleById(@Param("articleId") String articleId);
}
