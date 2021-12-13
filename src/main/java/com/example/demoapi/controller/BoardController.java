package com.example.demoapi.controller;

import com.example.demoapi.model.Board;
import com.example.demoapi.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // create board rest api
    @PostMapping("/boards")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // list all boards
    @GetMapping("/boards")
    public List<Board> listAllBoards() {
        return boardService.listAllBoards();
    }

    // get board by id
    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id);
    }

    // update board
    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> updateBoard(
            @PathVariable Integer id, @RequestBody Board boardDetails) {
        return boardService.updateBoard(id, boardDetails);
    }

    // delete board
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Integer id) {
        return boardService.deleteBoard(id);
    }
}
