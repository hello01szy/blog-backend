package com.blog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
@Data
public class BlogArticle {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Column(name = "author")
    private String author;
    @Column(name = "public")
    private String publicAttribute;
    @Column(name = "content")
    private String content;
    @Column(name = "cover")
    private String cover;
    @Column(name = "publish_date")
    private String publishDate;
    @Column(name = "publish_time")
    private String publishTime;
}
