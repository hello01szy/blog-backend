package com.blog.service.impl;

import antlr.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.blog.dao.BlogArticleDao;
import com.blog.dto.SecretArticleDto;
import com.blog.entity.BlogArticle;
import com.blog.entity.BlogModel;
import com.blog.service.BlogArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BlogArticleServiceImpl implements BlogArticleService {
    @Autowired
    private BlogArticleDao blogArticleDao;
    @Override
    public void insertArticle(BlogArticle blogArticle) {
        if (blogArticle.getId() == null || "".equals(blogArticle.getId())) {
            blogArticle.setId(IdUtil.simpleUUID());
        }
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

    @Override
    public List<BlogModel> getArticleList(String author, Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Map<String, Object>> models = blogArticleDao.getArticleList(author, pageNum, pageSize);
        List<BlogModel> blogModels = new ArrayList<>(4);
        for(Map<String, Object> model : models) {
            BlogModel blogModel = BeanUtil.fillBeanWithMap(model, new BlogModel(), false);
            blogModels.add(blogModel);
        }
        return blogModels;
    }

    @Override
    public int getCountsOfArticles() {
        return (int) blogArticleDao.count();
    }

    @Override
    public void deleteArticleById(List<String> ids) {
        blogArticleDao.deleteAllById(ids);
    }

    @Override
    public void secretArticle(SecretArticleDto secretArticleDto) {
        blogArticleDao.secretArticle(secretArticleDto.getId(), secretArticleDto.getFlag());
    }
}
