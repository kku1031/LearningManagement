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

    // http://www.naver.com/news/list.do?id=123&key=124&text=쿼리
    // https://
    // 프로토콜://도메인(IP) : ip와 주소 매핑/news/list.do?쿼리스트링(파라미터)
    //            ->www.naver.com:80(웹에서 포트번호 생략)으로 사용해서 드갈수 있게.

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email-auth";

    }
}
