package com.example.demoapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUtil {
    private String keyword;
    private String searchCode;
    private int page;
    private int size;
}
