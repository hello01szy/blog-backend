package com.blog.dao;

import com.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface BlogArticleDao extends JpaRepository<BlogArticle, String> {
    @Query(nativeQuery = true, value = "select * from blog where id = :articleId")
    List<BlogArticle> findArticleById(@Param("articleId") String articleId);
    @Query(value = "select id, ba.title, ba.is_public as isPublic, ba.publish_date as publishDate, ba.draft, ba.support, ba.collection, ba.read_count as readCount " +
            "from blog ba where author = :author limit :start, :pageSize", nativeQuery = true)
    List<Map<String, Object>> getArticleList(@Param("author") String author, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Query(value = "update blog set is_public = :flag where id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    int secretArticle(@Param("id") String id, @Param("flag") String flag);
}
