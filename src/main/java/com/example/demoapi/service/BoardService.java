package com.example.demoapi.service;

import com.example.demoapi.exception.ResourceNotFoundException;
import com.example.demoapi.mapper.BoardMapper;
import com.example.demoapi.model.Board;
import com.example.demoapi.model.SearchUtil;
import com.example.demoapi.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

//    @Autowired
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    // list all board with Pagination
    public ResponseEntity<?> selectAllPagedBoard(Pageable pageable) {
        Page<Board> result = boardRepository.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    // list all board
    public List<Board> selectAllBoard() {
        return boardRepository.findAll();
    }

    public Board selectBoard(@RequestBody Integer uid) {
        Board board = boardRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + uid));
        if(board.getUpdateDate() == null ||  board.getUpdateDate().isEmpty()){
            board.setUpdateDate("-");
        }
        return board;
    }

    // Insert board
    public Board createBoard(@RequestBody Board board) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setFrstRegistDate(dateFormat.format(date));
        return boardRepository.save(board);
    }

    // Update board
    public ResponseEntity<Board> updateBoard(@RequestBody Board boardDetails) {
        int id = boardDetails.getUid();
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        board.setUpdateDate(dateFormat.format(date));;

        board.setTitle(boardDetails.getTitle());
        board.setContent(boardDetails.getContent());

        Board updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }

    // Delete board
    public ResponseEntity<Map<String, Boolean>> deleteBoard(@PathVariable Integer uid) {
        Board board = boardRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + uid));

        boardRepository.delete(board);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Total Count
    public Long totalCntBoard() {
        return boardRepository.count();
    }


    public ResponseEntity<?> selectKeyword(SearchUtil item) {
        Long resultCnt = boardMapper.selectCount(item);
        List<Board> result = boardMapper.selectKeyword(item);

        HashMap<String, Object> map = new HashMap<>();
        map.put("resultList", result);
        map.put("resultCnt", resultCnt);

        return ResponseEntity.ok(map);
    }

    public ResponseEntity<?>  massiveInsert() {
        for(int i=1; i<31; i++) {
            Board item = new Board();
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            item.setTitle("test dummy"+i);
            item.setContent("test content for the dummy");
            item.setFrstRegistDate(dateFormat.format(date));

            boardRepository.save(item);
        }
        return ResponseEntity.ok("Massive insertion has completed");
    }
}
