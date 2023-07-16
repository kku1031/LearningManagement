package com.example.LearningManagement.member.controller;

import com.example.LearningManagement.member.model.MemberInput;
import com.example.LearningManagement.member.model.ResetPasswordInput;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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

        return "member/email_auth";

    }

    @GetMapping("/member/info")
    public String memberInfo(Model model, Principal principal) {

        return "member/info";
    }

    //로그인 : get이든 post든 다 받을 수 있게 처리
    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    //비밀번호 찾기
    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(
            //Result의 데이터 결과를 클라이언트에게 내려주기 위해서.
            Model model,
            ResetPasswordInput parameter) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    //비밀번호 초기화
    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        model.addAttribute("uuid", uuid);

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch (Exception e ) {
        }
        model.addAttribute("result", result);

        return "member/reset_password_result";
    }



}
