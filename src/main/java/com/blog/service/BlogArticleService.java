package com.blog.service;

import com.blog.dto.TagDto;
import com.blog.entity.BlogArticle;
import com.blog.entity.Tag;

import java.util.List;

public interface BlogArticleService {
    void insertArticle(BlogArticle blogArticle);
    BlogArticle getArticleById(String articleId);
    List<BlogArticle> getAllArticles();
}
