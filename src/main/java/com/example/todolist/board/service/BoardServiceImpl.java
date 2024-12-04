package com.example.todolist.board.service;

import com.example.todolist.board.entity.Board;
import com.example.todolist.board.entity.BoardDTO;
import com.example.todolist.board.repository.BoardRepository;
import com.example.todolist.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository BoardRepository, BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public List<Board> getAllBoard() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        return boardRepository.findByDeleted("N", sort);
    }

    @Override
    public BoardDTO getBoardById(Long id) {
        return boardRepository.findById(id)
                .map(board -> new BoardDTO(board.getId()
                        , board.getName()
                        , board.getTitle()
                        , board.getContent()
                        , board.getEmail()
                        , board.getCreatedDate()
                        , board.getUpdatedDate()))
                .orElse(null);
    }

    @Override
    public void createBoard(Board board) {
        /*
        Optional<Member> optionalMember = memberRepository.findByEmail(board.getEmail());
        optionalMember.ifPresent(member -> {
            board.setName(member.getName());  // Member의 이름을 Board에 설정
        });
        */
        boardRepository.save(board);
    }

    @Override
    public BoardDTO updateBoard(Long id, BoardDTO boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다.") );

        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
        return boardDto;
    }

    @Override
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setDeleted("Y");
        boardRepository.save(board);
    }

    /* 테이블 해당 행 완전 삭제
    @Override
    public void deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) boardRepository.deleteById(id);
        else throw new RuntimeException("게시글을 찾을 수 없습니다.");
    }*/
}
