package com.example.todolist.board.repository;

import com.example.todolist.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

//JpaRepository는 엔티티만 다루고, DTO는 서비스 계층에서 엔티티 데이터를 클라이언트에 맞게 변환한 후 사용하는 것이 일반적인 패턴임
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
    Optional<Board> findById(String name);
    List<Board> findByDeleted(String deleted, Sort sort);

}
