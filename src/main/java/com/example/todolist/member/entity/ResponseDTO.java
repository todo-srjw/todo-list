package com.example.todolist.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {

    private String message;
    private HttpStatus httpStatus;
}
