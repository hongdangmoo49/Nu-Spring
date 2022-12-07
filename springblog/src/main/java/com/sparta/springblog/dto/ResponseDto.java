package com.sparta.springblog.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private HttpStatus statusCode;
    private String msg;
    public ResponseDto(HttpStatus statusCode, String msg){
        this.statusCode = statusCode;
        this.msg = msg;
    }

}