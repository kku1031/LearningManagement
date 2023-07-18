package com.example.LearningManagement.main.controller;

import com.example.LearningManagement.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//MainPage 클래스 : 논리적주소(인터넷주소, 가상 주소)와 물리적주소(실제 파일) 매핑.
//http://www.naver.com/new/list.do
//하나의 주소에 대해서 어디서 누가 매핑해야 하는가? 메소드

@Controller //controller : 매핑하는 어노테이션.
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    //요청에 대한 매핑.
    @RequestMapping("/")
    public String main() {

//        String email = "kku1031@naver.com";
//        String subject = " 메일 발송 ";
//        String text = "<p> 메일 발송 성공 </p><p> !! </p>";
//
//        mailComponents.sendMail(email, subject, text);

        return "main";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }

}
