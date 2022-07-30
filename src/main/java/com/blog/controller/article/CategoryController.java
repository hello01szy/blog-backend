package com.blog.controller.article;

import com.blog.consistant.NetStatus;
import com.blog.dto.CategoryDto;
import com.blog.dto.CategoryFilterDto;
import com.blog.dto.DeleteCategoryDto;
import com.blog.entity.Category;
import com.blog.service.CategoryService;
import com.blog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/addCategory")
    public Response addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDate(categoryDto.getDate());
        categoryService.addCategory(category);
        return Response.builder().msg(NetStatus.SUCCESS.getMsg()).code(NetStatus.SUCCESS.getCode()).build();
    }

    @PostMapping("/getAllCategory")
    public Response getAllCategory(@RequestBody CategoryFilterDto categoryFilterDto) {
        Response response = Response.builder().build();
        response.setData(categoryService.getAllCategory(categoryFilterDto.getPage(), categoryFilterDto.getPageSize()));
        response.setMsg(NetStatus.SUCCESS.getMsg());
        response.setCode(NetStatus.SUCCESS.getCode());
        return response;
    }
    @PostMapping("/deleteCategory")
    public Response deleteCategory(@RequestBody DeleteCategoryDto deleteCategoryDto) {
        Response response = Response.builder().build();
        categoryService.deleteCategory(deleteCategoryDto.getIds());
        response.setCode(NetStatus.SUCCESS.getCode());
        response.setMsg(NetStatus.SUCCESS.getMsg());
        return response;
    }
}
