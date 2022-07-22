package com.blog.dao;

import com.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogArticleDao extends JpaRepository<BlogArticle, Integer> {
    @Query(nativeQuery = true, value = "select * from blog where id = :articleId")
    List<BlogArticle> findArticleById(@Param("articleId") String articleId);
    @Query(value = "select id, ba.title, ba.is_public as isPublic, ba.publish_date as publishDate, ba.draft, ba.support, ba.collection, ba.read_count as readCount " +
            "from blog ba where author = :author limit :start, :pageSize", nativeQuery = true)
    List<Map<String, Object>> getArticleList(@Param("author") String author, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
