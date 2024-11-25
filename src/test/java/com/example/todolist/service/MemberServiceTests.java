package com.example.todolist.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.repository.MemberRepository;
import com.example.todolist.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

//  @AfterEach
//    public void deleteAll() {
//        memberRepository.deleteAll();
//    }

    @Test
    public void signUpTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test4@gmail.com")
                .password("1234")
                .build();

        Member member = memberService.convertToEntity(memberDTO);

        memberService.signUpMember(member);

        assertTrue(memberRepository.existsByEmail(member.getEmail()), "성공");
    }

    @Test
    @Transactional
    public void signUpFailTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("test@gmail.com")
                .password("1234")
                .build();

        Member member = memberService.convertToEntity(memberDTO);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            memberService.signUpMember(member);
        });

        assertEquals("이미 사용중인 이메일입니다.", exception.getMessage());
    }
}
