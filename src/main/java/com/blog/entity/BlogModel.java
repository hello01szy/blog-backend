package com.blog.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class BlogModel implements Serializable {
    private String id;
    private String title;
    private String isPublic;
    private String publishDate;
    private int support;
    private int collection;
    private int readCount;
    private Integer draft;

    public BlogModel () {}
    public BlogModel(String id, String title, String isPublic, String publishDate, int support, int collection, int readCount, int draft) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.support = support;
        this.publishDate = publishDate;
        this.collection = collection;
        this.readCount = readCount;
        this.draft = draft;
    }
}
