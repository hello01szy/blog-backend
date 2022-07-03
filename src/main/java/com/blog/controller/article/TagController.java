package com.blog.controller.article;

import com.blog.consistant.NetStatus;
import com.blog.dto.TagDto;
import com.blog.entity.Tag;
import com.blog.service.TagService;
import com.blog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/addTag")
    public Response addTag(@RequestBody TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        tag.setDate(tagDto.getDate());
        tagService.addTag(tag);
        return Response.builder().msg(NetStatus.SUCCESS.getMsg()).code(NetStatus.SUCCESS.getCode()).build();
    }

    @GetMapping("/getAllTags")
    public Response getAllTags() {
        Response response = Response.builder().build();
        response.setData(tagService.getAllTags());
        response.setMsg(NetStatus.SUCCESS.getMsg());
        response.setCode(NetStatus.SUCCESS.getCode());
        return response;
    }
}
