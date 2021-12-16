package com.example.demoapi.service;

import com.example.demoapi.exception.ResourceNotFoundException;
import com.example.demoapi.model.Board;
import com.example.demoapi.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // list all boards
    public Page<Board> listAllPagedBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    // list all boards
    public List<Board> listAllBoards() {
        return boardRepository.findAll();
    }

    // create board rest api
    public Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    // get board by id
    public ResponseEntity<Board> getBoardById(@PathVariable Integer id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        return ResponseEntity.ok(board);
    }

    // update board
    public ResponseEntity<Board> updateBoard(@RequestBody Board boardDetails) {
        int id = boardDetails.getUid();
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        board.setTitle(boardDetails.getTitle());
        board.setContent(boardDetails.getContent());

        Board updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }

    // delete board
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Integer uid) {
        Board board = boardRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + uid));

        boardRepository.delete(board);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
