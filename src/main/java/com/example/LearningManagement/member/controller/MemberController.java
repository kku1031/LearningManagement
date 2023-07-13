package com.example.LearningManagement.member.controller;

import com.example.LearningManagement.member.entity.Member;
import com.example.LearningManagement.member.model.MemberInput;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    //request web -> server
    //response server -> web
    @PostMapping("/member/register")
    //스프링이 request와 response에 대해서 의존성 주입함.
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);

        return "member/register_complete";
    }
}
