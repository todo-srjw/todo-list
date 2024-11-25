package com.example.todolist.member.Handler;

import com.example.todolist.member.controller.MemberController;
import com.example.todolist.member.entity.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SignUpHandler {

    public ResponseEntity<ResponseDTO> getRedirectResponse(String message, HttpStatus httpStatus, String redirectUrl) {
        ResponseDTO response = new ResponseDTO(message, httpStatus);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Location", redirectUrl);

        return ResponseEntity.status(response.getHttpStatus()).headers(headers).body(response);
    }

    public ResponseEntity<ResponseDTO> getResponse(String message, HttpStatus httpStatus) {
        ResponseDTO response = new ResponseDTO(message, httpStatus);

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
