package com.example.todolist.security.controller;

import com.example.todolist.common.MessageConstants;
import com.example.todolist.member.Handler.ResponseHandler;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.entity.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo/member")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final ResponseHandler responseHandler;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/signIn")
    public String signIn() {
        return "todo/member/signIn";
    }

    @PostMapping("/signIn")
    public ResponseEntity<ResponseDTO> signIn(@Valid @RequestBody MemberDTO memberDTO, HttpSession session) {
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
            //세션처리
            session.setAttribute("user", authenticated.getName());

            log.info("success!!");

            // 로그인 성공 후 리다이렉트할 URL
            return responseHandler.getRedirectResponse(MessageConstants.SUCCESS_SIGN_IN, HttpStatus.OK,authenticated.getName(), MessageConstants.MAIN_PAGE_URL);

        } catch (BadCredentialsException e) {
            log.info("fail!!");

            // 로그인 실패 시 에러 메시지 전달
            return responseHandler.getResponse(MessageConstants.FAIL_SIGN_IN, HttpStatus.BAD_REQUEST);
        }

    }

}
