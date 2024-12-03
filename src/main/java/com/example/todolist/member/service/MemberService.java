package com.example.todolist.member.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;

public interface MemberService {

    void signUpMember(Member member);

    MemberDTO convertToDto(Member member);

    Member convertToEntity(MemberDTO memberDTO);

    boolean existsByEmail(String email);
}
