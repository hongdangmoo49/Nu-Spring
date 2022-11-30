package com.sparta.springnoob.controller;

import com.sparta.springnoob.dto.*;
import com.sparta.springnoob.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {

        return blogService.createBlog(requestDto);
    }

    @GetMapping("/api/blogs")
    public BlogListResponseDto getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/api/blogs/{id}")
    public BlogReadDto getBlogsOne(@PathVariable Long id) {
        return blogService.getBlogsOne(id);
    }

    @PutMapping("/api/blogs/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.update(id, requestDto);
    }

    @DeleteMapping("/api/blogs/{id}")
    public ResponseDto deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        return blogService.deleteBlog(id, requestDto);
    }
}
