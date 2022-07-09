package com.blog.controller.article;

import com.blog.consistant.NetStatus;
import com.blog.dto.DeleteTagsDto;
import com.blog.dto.TagDto;
import com.blog.dto.TagFilterDto;
import com.blog.entity.Tag;
import com.blog.service.TagService;
import com.blog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getAllTags")
    public Response getAllTags(@RequestBody TagFilterDto tagFilterDto) {
        Response response = Response.builder().build();
        response.setData(tagService.getAllTags(tagFilterDto.getPage(), tagFilterDto.getPageSize()));
        response.setMsg(NetStatus.SUCCESS.getMsg());
        response.setCode(NetStatus.SUCCESS.getCode());
        return response;
    }
    @PostMapping("/deleteTags")
    public Response deleteTags(@RequestBody DeleteTagsDto deleteTagsDto) {
        Response response = Response.builder().build();
        tagService.deleteTags(deleteTagsDto.getTagsId());
        response.setCode(NetStatus.SUCCESS.getCode());
        response.setMsg(NetStatus.SUCCESS.getMsg());
        return response;
    }
}
