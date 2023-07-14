package com.example.LearningManagement.member.controller;

import com.example.LearningManagement.member.model.MemberInput;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
    //Model : 클라이언트에게 데이터를 내리기 위해 사용하는 Interface
    public String registerSubmit(Model model, HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }
}
