package com.example.demoapi.controller;

import com.example.demoapi.model.Board;
import com.example.demoapi.service.BoardService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //list all boards with paging
    @GetMapping("/pagedBoard")
    public Page<Board> selectAllPagedBoard(Pageable pageable, @RequestParam int page, @RequestParam int size) {
        pageable = PageRequest.of(page, size);
        return boardService.selectAllPagedBoard(pageable);
    }

    // list all boards
    @GetMapping("/board")
    public List<Board> selectAllBoard() {
        return boardService.selectAllBoard();
    }

    // create board
    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // update board
    @PutMapping("/updateBoard")
    public ResponseEntity<Board> updateBoard(@RequestBody Board boardDetails) {
        return boardService.updateBoard(boardDetails);
    }

    // delete board
    @DeleteMapping("/deleteBoard/{uid}")
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Integer uid) {
        return boardService.deleteBoard(uid);
    }

}