package com.example.todolist.member.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import com.example.todolist.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signUpMember(Member member) {
        try {
            if (memberRepository.existsByEmail(member.getEmail())) {
                return "이메일 중복";
            }
            memberRepository.save(member);

            return "success";
        } catch (DataAccessException e) {
            return "DB ERROR";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    @Override
    public MemberDTO convertToDto(Member member) {
        return MemberDTO.builder()
                .mno(member.getMno())
                .email(member.getEmail())
                .build();

    }

    @Override
    public Member convertToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .build();
    }
}
