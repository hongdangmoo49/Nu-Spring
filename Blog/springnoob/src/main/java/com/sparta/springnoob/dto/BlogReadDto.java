package com.sparta.springnoob.dto;

import com.sparta.springnoob.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogReadDto {
    private Long id;
    private String username;
    private String contents;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public BlogReadDto(Blog blog) {
        this.id = blog.getId();
        this.contents = blog.getContents();
        this.username = blog.getUsername();
        this.title    = blog.getTitle();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}