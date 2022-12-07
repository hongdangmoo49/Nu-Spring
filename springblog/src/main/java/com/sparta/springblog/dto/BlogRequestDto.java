package com.sparta.springblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;
}

