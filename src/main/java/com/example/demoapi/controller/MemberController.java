package com.example.demoapi.controller;

import com.example.demoapi.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/member/api")
public class MemberController {

    /*@Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> listAllMembers() {return memberService.listAllMembers();}*/
}
