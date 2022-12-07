package com.sparta.springblog.service;


import com.sparta.springblog.dto.*;
import com.sparta.springblog.entity.Blog;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BlogResponseDto createBlog(BlogRequestDto requestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰 검사
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Blog blog = new Blog(requestDto, user.getUsername());
            blogRepository.save(blog);
            return new BlogResponseDto(blog);
        }
        throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
    }

    @Transactional(readOnly = true)
    public BlogListResponseDto getBlogs() {
        BlogListResponseDto blogListResponseDto = new BlogListResponseDto();
        List<Blog> blogs = blogRepository.findAllByOrderByModifiedAtDesc();
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
    public BlogResponseDto update(Long id, BlogRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰 검사
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Blog blog = blogRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않거나, 패스워드가 일치하지 않습니다.")
            );
            blog.update(requestDto);
            return new BlogResponseDto(blog);
        }
        throw new IllegalArgumentException("수정 실패");
    }



    @Transactional
    public ResponseDto deleteBlog(Long id, BlogRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰 검사
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            blogRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 없습니다.")
            );
            blogRepository.deleteById(id);
            return new ResponseDto(HttpStatus.OK, "삭제가 완료되었습니다.");
        }
        throw new IllegalArgumentException("삭제 실패");
    }

    private Blog checkBlogs(BlogRepository blogRepository, Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new RuntimeException("아이디를 찾을 수 없습니다.")
        );
    }
}
