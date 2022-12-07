package com.sparta.springblog.repository;


import com.sparta.springblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtAsc();
    Optional<Blog> findByIdAndPassword(Long id, String password);
    Optional<Blog> findById(Long id);

    List<Blog> findAllByOrderByModifiedAtDesc();
}