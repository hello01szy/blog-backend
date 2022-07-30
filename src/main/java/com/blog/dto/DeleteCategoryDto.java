package com.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteCategoryDto {
    private List<String> ids;
}
