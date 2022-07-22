package com.blog.dto;

import lombok.Data;

@Data
public class BlogListDto {
    private String author;
    private int pageNum;
    private int pageSize;
}
