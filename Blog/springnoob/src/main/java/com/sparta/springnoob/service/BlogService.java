package com.sparta.springnoob.service;


import com.sparta.springnoob.dto.BlogRequestDto;
import com.sparta.springnoob.dto.ResponseDto;
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
    public Blog createBlog(BlogRequestDto requestDto){

        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    @Transactional(readOnly = true)
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional(readOnly = true)
    public List<Blog> getBlogsNumber() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalIdentifierException("아이디가 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return blog.getId();
    }

    @Transactional
    public ResponseDto deleteBlog(Long id, BlogRequestDto reqeustDto) {
        blogRepository.findByIdAndPassword(id, reqeustDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("패스워드가 틀렸습니다.")
        );
        blogRepository.deleteById(id);
        return new ResponseDto("삭제가 완료되었습니다.");
    }
}
