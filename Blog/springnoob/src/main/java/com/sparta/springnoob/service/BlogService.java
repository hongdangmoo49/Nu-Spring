package com.sparta.springnoob.service;


import com.sparta.springnoob.dto.*;
import com.sparta.springnoob.entity.Blog;
import com.sparta.springnoob.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public BlogResponseDto createBlog(BlogRequestDto requestDto){

        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return new BlogResponseDto(blog);
    }

    @Transactional(readOnly = true)
    public BlogListResponseDto getBlogs() {
        BlogListResponseDto blogListResponseDto = new BlogListResponseDto();
        List<Blog> blogs = blogRepository.findAllByOrderByCreatedAtAsc();
        for (Blog blog : blogs) {
            blogListResponseDto.addBlog(new BlogReadDto(blog));
        }
        return blogListResponseDto;
    }

    public BlogReadDto getBlogsOne(Long id) {
        Blog blog = checkBlogs(blogRepository, id);
        return new BlogReadDto(blog);
    }

    @Transactional
    public BlogResponseDto update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않거나, 패스워드가 일치하지 않습니다.")
        );
        blog.update(requestDto);
        return new BlogResponseDto(blog);
    }

    @Transactional
    public ResponseDto deleteBlog(Long id, BlogRequestDto requestDto) {
        blogRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("패스워드가 틀렸습니다.")
        );
        blogRepository.deleteById(id);
        return new ResponseDto("삭제가 완료되었습니다.");
    }
    private Blog checkBlogs(BlogRepository blogRepository, Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new RuntimeException("아이디를 찾을 수 없습니다.")
        );
    }
}
