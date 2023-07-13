package com.example.LearningManagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//MainPage 클래스 : 논리적주소(인터넷주소, 가상 주소)와 물리적주소(실제 파일) 매핑.
//http://www.naver.com/new/list.do
//하나의 주소에 대해서 어디서 누가 매핑해야 하는가? 메소드

@Controller //controller : 매핑하는 어노테이션.
public class MainController {

    //요청에 대한 매핑.
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
