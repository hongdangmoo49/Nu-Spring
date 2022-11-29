package com.sparta.springnoob.entity;

import com.sparta.springnoob.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;


    public Blog (BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title    = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(BlogRequestDto blogRequestDto) {
        this.username = blogRequestDto.getUsername();
        this.contents = blogRequestDto.getContents();
        this.title    = blogRequestDto.getTitle();
    }

    public void delete(BlogRequestDto blogRequestDto){
        this.username = blogRequestDto.getUsername();
        this.password = blogRequestDto.getPassword();
    }
}