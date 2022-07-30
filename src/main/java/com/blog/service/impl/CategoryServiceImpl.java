package com.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.blog.dao.CategoryDao;
import com.blog.entity.Category;
import com.blog.entity.Tag;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public void addCategory(Category category) {
        if (StringUtils.hasLength(category.getId())) {
            Category oldCategory = categoryDao.getById(category.getId());
            oldCategory.setName(category.getName());
            categoryDao.save(oldCategory);
        } else {
            String id = IdUtil.simpleUUID();
            category.setId(id);
            categoryDao.save(category);
        }
    }

    @Override
    public Page<Category> getAllCategory(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return categoryDao.findAll(pageable);
    }

    @Override
    public void deleteCategory(List<String> category) {
        categoryDao.deleteAllById(category);
    }
}
