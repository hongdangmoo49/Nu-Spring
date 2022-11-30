package com.sparta.springnoob.dto;

import com.sparta.springnoob.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
public class BlogListResponseDto {
    List<BlogReadDto> blogList = new ArrayList<>();
    public void addBlog(BlogReadDto blogReadDto){
        blogList.add(blogReadDto);
    }
}

