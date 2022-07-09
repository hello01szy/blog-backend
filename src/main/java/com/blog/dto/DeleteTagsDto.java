package com.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteTagsDto {
    private List<String> tagsId;
}
