package com.example.todolist.security.controller;

import com.example.todolist.member.Handler.ResponseHandler;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo")
public class AuthController {

    private final ResponseHandler responseHandler;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/signIn")
    public String signIn() {
        return "todo/signIn";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute MemberDTO memberDTO) {
        try {
            Member member = Member.builder()
                    .email(memberDTO.getEmail())
                    .password(memberDTO.getPassword())
                    .build();

            // 사용자 인증을 위한 UsernamePasswordAuthenticationToken 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());

            // AuthenticationManager를 통해 인증 시도
            Authentication authenticated = authenticationManager.authenticate(authentication);

            // 인증이 성공하면 SecurityContext에 인증된 사용자 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authenticated);

            log.info("success!!");

            // 로그인 성공 후 리다이렉트할 URL
            return "redirect:/";

        } catch (BadCredentialsException e) {
            log.info("fail!!");

            // 로그인 실패 시 에러 메시지 전달
            return "redirect:/signIn?error=true";
        }

    }

}
