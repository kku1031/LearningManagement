package com.example.LearningManagement.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

    @GetMapping("/member/register")
    public String register() {
        System.out.println("주소 새로고침");
        return "member/register";
    }

    //request web -> server
    //response server -> web
    @PostMapping("/member/register")
    //스프링이 request와 response에 대해서 의존성 주입함.
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response
            , MemberInput parameter) {

        System.out.println(parameter.toString());

        return "member/register";
    }
}
