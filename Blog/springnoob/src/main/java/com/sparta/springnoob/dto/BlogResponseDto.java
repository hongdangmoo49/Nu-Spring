package com.sparta.springnoob.dto;

import com.sparta.springnoob.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String title;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.contents = blog.getContents();
        this.username = blog.getUsername();
        this.title    = blog.getTitle();
        this.password = blog.getPassword();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}
