package com.example.LearningManagement.admin.controller;

import com.example.LearningManagement.admin.dto.MemberDto;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model) {

        List<MemberDto> members = memberService.list();

        model.addAttribute("list", members);

        return "admin/member/list";
    }
}
