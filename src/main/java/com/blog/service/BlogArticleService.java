package com.blog.service;

import com.blog.entity.BlogArticle;
import com.blog.entity.BlogModel;

import java.util.List;

public interface BlogArticleService {
    void insertArticle(BlogArticle blogArticle);
    BlogArticle getArticleById(String articleId);
    List<BlogArticle> getAllArticles();
    List<BlogModel> getArticleList(String author, Integer pageNum, Integer pageSize);
}
