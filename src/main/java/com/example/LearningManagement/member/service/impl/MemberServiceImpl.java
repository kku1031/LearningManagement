package com.example.LearningManagement.member.service.impl;

import com.example.LearningManagement.components.MailComponents;
import com.example.LearningManagement.member.entity.Member;
import com.example.LearningManagement.member.model.MemberInput;
import com.example.LearningManagement.member.repository.MemberRepository;
import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        //DB에 설정된 비밀번호 : 1234 !=  화면 UI **** => 인코딩 맞춰야함. ->  설정 이후 DB도 암호화해서 저장됨.
        //password 해쉬 저장해야함
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        //UUID : 고유한 아이디(메일인증)
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                //.password(parameter.getPassword())를 이렇게 수정
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                //회원가입 시 아무도 알수 없는 키값 생성.
                .emailAuthKey(uuid)
                .build();

        memberRepository.save(member);

        //회원가입 후 메일 전송
        String email = parameter.getUserId();
        String subject = "학습관리시스템 사이트 가입을 축하드립니다.";
        String text = "<p>학습관리시스템 사이트 가입을 축하드립니다.</p>" +
                "<p>아래링크를 클릭하셔서 가입을 완료하세요</p>" +
                "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);
        return true;
    }

    //회원가입 계정 활성화
    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    /**
     * 스프링 시큐리티의 loadUserByUsername 메소드를 재정의.
     * 주어진 사용자 이름에 해당하는 회원 정보를 찾아서 UserDetails 타입으로 반환.
     *
     * @param username 사용자 이름 (이메일).
     * @return UserDetails 타입의 객체로 변환된 회원 정보.
     * @throws UsernameNotFoundException 주어진 사용자 이름에 해당하는 회원 정보가 없을 경우 예외.
     */

    //회원정보 return
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //username = id(email)
        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER`"));

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}