package com.example.todolist.member.repository;

import com.example.todolist.member.entity.Member;
import com.example.todolist.member.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
