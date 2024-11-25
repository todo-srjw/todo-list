package com.example.todolist.member.service;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {

        Member save = memberRepository.save(member);

        return save;
    }
}
