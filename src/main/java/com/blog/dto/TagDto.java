package com.blog.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TagDto {
    private String id;
    private String name;
    private String date;
}
