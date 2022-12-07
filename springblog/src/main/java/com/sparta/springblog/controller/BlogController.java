package com.sparta.springblog.controller;

import com.sparta.springblog.dto.*;
import com.sparta.springblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/blogs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto, HttpServletRequest request) {

        return blogService.createBlog(requestDto, request);
    }

    @GetMapping("/api/blogs")
    public com.sparta.springblog.dto.BlogListResponseDto getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/api/blogs/{id}")
    public BlogReadDto getBlogsOne(@PathVariable Long id) {
        return blogService.getBlogsOne(id);
    }

    @PutMapping("/api/blogs/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, HttpServletRequest request) {
        return blogService.update(id, requestDto, request);
    }

    @DeleteMapping("/api/blogs/{id}")
    public ResponseDto deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, HttpServletRequest request){
        return blogService.deleteBlog(id, requestDto, request);
    }
}
