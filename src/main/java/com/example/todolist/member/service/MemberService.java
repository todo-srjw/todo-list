package com.example.todolist.member.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;

public interface MemberService {

    String signUpMember(Member member);

    MemberDTO convertToDto(Member member);

    Member convertToEntity(MemberDTO memberDTO);
}
