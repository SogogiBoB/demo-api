package com.example.demoapi.mapper;

import com.example.demoapi.model.Board;
import com.example.demoapi.model.SearchUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> selectKeyword(SearchUtil item);

    Long selectCount(SearchUtil item);
}