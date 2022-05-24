package com.example.demoapi.controller;

import com.example.demoapi.model.Board;
import com.example.demoapi.model.SearchUtil;
import com.example.demoapi.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/api")
public class BoardController {

//    @Autowired
    private final BoardService boardService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //list all boards with paging
    @GetMapping("/pagedBoard")
    @ResponseBody
    public ResponseEntity<?> selectAllPagedBoard(
            Pageable pageable,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "searchCode", required = false) String searchCode
    ) {
        log.debug("openBoardList");

        //index가 0부터 시작하기 때문에 -1을 해줘야 첫 페이지부터 출력됨
        int pageForJpa = page-1;
        pageable = PageRequest.of(pageForJpa, 10);

        if(keyword == null || keyword.isEmpty()) {
            return boardService.selectAllPagedBoard(pageable);

        } else {
            SearchUtil item = new SearchUtil();

            item.setSearchCode(searchCode);
            item.setKeyword(keyword);
            item.setPage(page-1);
            item.setSize(10);

            return boardService.selectKeyword(item);
        }
    }

    // list all boards
    @GetMapping("/boardList.json")
    @ResponseBody
    public List <Board> selectAllBoard() {
        List <Board> list = boardService.selectAllBoard();
        return list;
    }

    // get a board
    @GetMapping("/selectBoard.json/{uid}")
    @ResponseBody
    public Board selectBoard(@PathVariable Integer uid) {
        return boardService.selectBoard(uid);
    }

    // create board
    @PostMapping("/insertBoard.json")
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

    @PostMapping("/massiveInsert")
    public ResponseEntity<?> massiveInsert() {
        return boardService.massiveInsert();
    }
}