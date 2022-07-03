package com.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.blog.dao.BlogArticleDao;
import com.blog.dto.TagDto;
import com.blog.entity.BlogArticle;
import com.blog.entity.Tag;
import com.blog.service.BlogArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogArticleServiceImpl implements BlogArticleService {
    @Autowired
    private BlogArticleDao blogArticleDao;
    @Override
    public void insertArticle(BlogArticle blogArticle) {
        blogArticle.setId(IdUtil.simpleUUID());
        blogArticle.setPublicAttribute("1");
        blogArticleDao.save(blogArticle);
    }

    @Override
    public BlogArticle getArticleById(String articleId) {
        List<BlogArticle> blogs = blogArticleDao.findArticleById(articleId);
        return blogs.get(0);
    }

    @Override
    public List<BlogArticle> getAllArticles() {
        return blogArticleDao.findAll();
    }
}
