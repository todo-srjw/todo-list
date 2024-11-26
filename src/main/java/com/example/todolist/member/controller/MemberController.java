package com.example.todolist.member.controller;

import com.example.todolist.member.Handler.ResponseHandler;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.entity.ResponseDTO;
import com.example.todolist.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseHandler signUpHandler;

    private static final String SUCCESS_MESSAGE = "회원가입 성공";
    private static final String MAIN_PAGE_URL = "/main";

    @GetMapping("/signUp")
    public String signUp() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody MemberDTO memberDTO) {

        Member member = memberService.convertToEntity(memberDTO);

        try {
            memberService.signUpMember(member);
            return signUpHandler.getRedirectResponse(SUCCESS_MESSAGE, HttpStatus.CREATED, MAIN_PAGE_URL);

        } catch (IllegalStateException e) {
            return signUpHandler.getResponse(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
