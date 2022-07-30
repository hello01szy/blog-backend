package com.blog.service;

import com.blog.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    Page<Category> getAllCategory(Integer page, Integer pageSize);
    void deleteCategory(List<String> category);
}
