package com.blog.service;

import com.blog.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);
    List<Tag> getAllTags();
}
