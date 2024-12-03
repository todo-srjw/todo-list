package com.example.todolist.member.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signUpSuccessTest() throws Exception {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test@gmail.com")
                .password("12345")
                .build();

        mockMvc.perform(post("/todo/member/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberDTO)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    @Transactional
    public void signUpFailTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test1@gmail.com")
                .password("1234")
                .build();

        Member member = memberService.convertToEntity(memberDTO);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            memberService.signUpMember(member);
        });

        assertEquals("이미 사용중인 이메일입니다.", exception.getMessage());
    }

    @Test
    public void signInSuccessTest() throws Exception {
        mockMvc.perform(post("/todo/member/signIn")
                        .param("username", "test@gmail.com") // Spring Security가 기대하는 파라미터
                        .param("password", "12345") // Spring Security가 기대하는 파라미터
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF 토큰
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/")); // 로그인 성공 후 리디렉션
    }

    @Test
    public void signInFailTest() throws Exception {
        mockMvc.perform(post("/todo/member/signIn")  // 경로를 맞춤
                        .param("username", "test@gmail.com") // Spring Security가 기대하는 파라미터
                        .param("password", "123456") // Spring Security가 기대하는 파라미터
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF 토큰
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todo/member/signIn?error"));  // 리다이렉트 URL 맞추기
    }

    @Test
    public void signOutTest() throws Exception {
        //1. 로그인
        mockMvc.perform(post("/todo/member/signIn")
                        .param("username", "test@gmail.com") // Spring Security가 기대하는 파라미터
                        .param("password", "12345") // Spring Security가 기대하는 파라미터
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF 토큰
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/")); // 로그인 성공 후 리디렉션

        // 2. 로그아웃 요청
        mockMvc.perform(post("/todo/member/signOut")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF 토큰
                .andExpect(status().is3xxRedirection()) // 로그아웃 성공 후 리디렉션
                .andExpect(redirectedUrl("/")); // 로그아웃 후 이동할 URL
    }
}
