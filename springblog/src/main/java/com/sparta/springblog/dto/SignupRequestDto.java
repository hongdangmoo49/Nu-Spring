package com.sparta.springblog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "아이디에 공백이 있거나 값을 입력하지 않았습니다.")
//    @Size(min = 4, max = 10, message = "아이디가 너무 길거나 짧습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}",
            message = "아이디는 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호에 공백이 있거나 값을 입력하지 않았습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}",
            message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.")
    private String password;

    private String email;

    private boolean admin = false;

    private String adminToken = "";




}