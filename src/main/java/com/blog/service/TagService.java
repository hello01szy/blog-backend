package com.blog.service;

import com.blog.entity.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);
    Page<Tag> getAllTags(Integer page, Integer pageSize);
    void deleteTags(List<String> tags);
}
