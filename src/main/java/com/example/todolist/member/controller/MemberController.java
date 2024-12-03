package com.example.todolist.member.controller;

import com.example.todolist.common.MessageConstants;
import com.example.todolist.member.Handler.ResponseHandler;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.entity.ResponseDTO;
import com.example.todolist.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseHandler responseHandler;

    @GetMapping("/signUp")
    public String signUp() {
        return "todo/signUp";
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO> signUp(@Valid @RequestBody MemberDTO memberDTO, Errors errors) {

        if (errors.hasErrors()) {
            return responseHandler.getResponse(MessageConstants.VALID_FAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        Member member = memberService.convertToEntity(memberDTO);

        try {

            memberService.signUpMember(member);
            return responseHandler.getRedirectResponse(MessageConstants.SUCCESS_MESSAGE, HttpStatus.CREATED, MessageConstants.MAIN_PAGE_URL);

        } catch (IllegalStateException e) {
            return responseHandler.getResponse(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/check-email")
    public ResponseEntity<ResponseDTO> isEmailDuplicate(String email) {
        if (memberService.existsByEmail(email)) {
            return responseHandler.getResponse(MessageConstants.EMAIL_DUPLICATE_MESSAGE, HttpStatus.CONFLICT);
        } else{
            return responseHandler.getResponse(MessageConstants.EMAIL_AVAILABLE_MESSAGE, HttpStatus.OK);
        }
    }
}
