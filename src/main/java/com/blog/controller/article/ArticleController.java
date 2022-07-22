package com.blog.controller.article;

import com.blog.consistant.NetStatus;
import com.blog.dto.BlogListDto;
import com.blog.entity.BlogArticle;
import com.blog.entity.BlogListWrapper;
import com.blog.entity.BlogModel;
import com.blog.service.BlogArticleService;
import com.blog.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ArticleController {
    /** 上传图片路径 */
    @Value("${file.upload.picPath}")
    private String uploadPath;
    /** 上传图片的url前缀 */
    @Value("${file.upload.picBaseUrl}")
    private String uploadBaseUrl;

    @Autowired
    BlogArticleService blogArticleService;

    @ResponseBody
    @PostMapping("/uploadArticleHeadCover")
    public Response uploadArticleHeadCover(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        File filePath = new File(uploadPath);
        if (!filePath.exists() && !filePath.isDirectory()) {
            log.error("{}, the directory isn't exists", uploadPath);
            filePath.mkdir();
        }
        String originalFileName = file.getOriginalFilename();
        log.info("original filename is: {}", originalFileName);
        // 获取文件类型
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        // 设置文件新名称，当前时间+文件名称
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String fileName = name + dateStr + "." + type;
        log.info("new filename is: {}", fileName);
        // 指定路径下创建文件
        File targetFile = new File(filePath, fileName);
        Response response;
        try {
            file.transferTo(targetFile);
            response = Response.builder()
                    .msg(uploadBaseUrl + fileName)
                    .code(NetStatus.SUCCESS.getCode())
                    .build();
        } catch (IOException e) {
            log.error("file uplaod exception", e.getMessage());
            response = Response.builder().msg(e.getMessage())
                    .code(NetStatus.FILE_UPLOAD_FILED.getCode())
                    .build();
        }
        return response;
    }

    @PostMapping("/createArticle")
    public Response createArticle(@RequestBody BlogArticle blogArticle) {
        blogArticleService.insertArticle(blogArticle);
        return Response
                .builder()
                .msg(NetStatus.SUCCESS.getMsg())
                .code(NetStatus.SUCCESS.getCode())
                .build();
    }
    @PostMapping("/getArticleById")
    public Response getArticle(@RequestBody Map<String, String> param) {
        BlogArticle article = blogArticleService.getArticleById(param.get("articleId"));
        return Response.builder()
                .msg(NetStatus.SUCCESS.getMsg())
                .code(NetStatus.SUCCESS.getCode())
                .data(article)
                .build();
    }
    @GetMapping("/getAllArticles")
    public Response getAllArticles() {
        List<BlogArticle> articles = blogArticleService.getAllArticles();
        return Response
                .builder()
                .msg(NetStatus.SUCCESS.getMsg())
                .code(NetStatus.SUCCESS.getCode())
                .data(articles)
                .build();
    }
    @PostMapping("/getArticleList")
    public Response getArticleList(@RequestBody BlogListDto blogListDto) {
        List<BlogModel> articles = blogArticleService.getArticleList(blogListDto.getAuthor(),
                blogListDto.getPageNum(), blogListDto.getPageSize());
        BlogListWrapper blogListWrapper = BlogListWrapper.builder().modelList(articles).count(articles.size()).build();
        return Response.builder().msg(NetStatus.SUCCESS.getMsg())
                .code(NetStatus.SUCCESS.getCode())
                .data(blogListWrapper)
                .build();
    }
    @PostMapping("/updateArticleById")
    public Response updateArticleById(@RequestBody Map<String, String> param) {
        BlogArticle article = blogArticleService.getArticleById(param.get("articleId"));
        return Response.builder()
                .msg(NetStatus.SUCCESS.getMsg())
                .code(NetStatus.SUCCESS.getCode())
                .data(article)
                .build();
    }
}
