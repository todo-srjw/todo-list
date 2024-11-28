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
                .email("test2@gmail.com")
                .password("12345")
                .build();

        mockMvc.perform(post("/todo/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberDTO)))
                .andExpect(status().isConflict())
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
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test1@gmail.com")
                .password("1234")
                .build();

        mockMvc.perform(post("/todo/signIn")  // 경로를 맞춤
                        .flashAttr("memberDTO", memberDTO)
                        .with(user("test1@gmail.com").password("1234")))  // 이메일 일치
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));  // 리다이렉트 URL 맞추기
    }

    @Test
    public void signInFailTest() throws Exception {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test1@gmail.com")
                .password("12345")
                .build();

        mockMvc.perform(post("/todo/signIn")  // 경로를 맞춤
                        .flashAttr("memberDTO", memberDTO)
                        .with(user("test1@gmail.com").password("12345")))  // 이메일 일치
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signIn?error=true"));  // 리다이렉트 URL 맞추기
    }
}
