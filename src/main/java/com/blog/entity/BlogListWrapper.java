package com.blog.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BlogListWrapper {
    private int count;
    private List<BlogModel> modelList;
}
