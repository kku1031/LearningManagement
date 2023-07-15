package com.example.LearningManagement.member.service.impl;

import com.example.LearningManagement.components.MailComponents;
import com.example.LearningManagement.member.entity.Member;
import com.example.LearningManagement.member.model.MemberInput;
import com.example.LearningManagement.member.repository.MemberRepository;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;


    //회원가입
    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if (optionalMember.isPresent()) {
            //현재 userId에 해당하는 데이터 존재
            return false;
        }

        String uuid = UUID.randomUUID().toString();

        Member member = new Member();

        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());
        member.setEmailAuthYn(false);
        //회원가입 시 아무도 알수 없는 키값 생성.
        member.setEmailAuthKey(uuid);
        memberRepository.save(member);

        //회원가입 후 메일 전송
        String email = parameter.getUserId();
        String subject = "학습관리시스템 사이트 가입을 축하드립니다.";
        String text = "<p>학습관리시스템 사이트 가입을 축하드립니다.</p>" +
                "<p>아래링크를 클릭하셔서 가입을 완료하세요</p>" +
                "<div><a href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
        return true;
    }
}
