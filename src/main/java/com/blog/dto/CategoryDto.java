package com.blog.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDto {
    private String id;
    private String name;
    private String date;
}
