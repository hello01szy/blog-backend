package com.blog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "date")
    private String date;
    @Column(name = "author")
    private String author;
}
