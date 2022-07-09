package com.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.blog.dao.TagDao;
import com.blog.entity.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    public void addTag(Tag tag) {
        if (StringUtils.hasLength(tag.getId())) {
            Tag oldTag = tagDao.getById(tag.getId());
            oldTag.setName(tag.getName());
            tagDao.save(oldTag);
        } else {
            String id = IdUtil.simpleUUID();
            tag.setId(id);
            tagDao.save(tag);
        }
    }

    @Override
    public Page<Tag> getAllTags(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return tagDao.findAll(pageable);
    }

    @Override
    public void deleteTags(List<String> tags) {
        tagDao.deleteAllById(tags);
    }
}
