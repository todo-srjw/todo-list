package com.example.todolist.member.controller;

import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/register")
    public String register(MemberDTO memberDTO) {
        return "redirect:/login?registerSuccess=true";
    }

}
