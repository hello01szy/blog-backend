package com.blog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecretArticleDto {
    private String id;
    private String flag;
}
