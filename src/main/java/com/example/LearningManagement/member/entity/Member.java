package com.example.LearningManagement.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements MemberCode {

    @Id
    private String userId;
    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    //메일 인증 유무
    private boolean emailAuthYn;

    //실제 이메일 인증 날짜
    private LocalDateTime emailAuthDt;

    //회원가입시 키 생성 -> 만들어진 키 email로 전송 -> email로 접속 성공 시(emailAuthYn = true)
    private String emailAuthKey;

    //비밀번호 초기화 키 생성
    private String resetPasswordKey;

    //특정기간 지나면 초기화 안됨
    private LocalDateTime resetPasswordLimitDt;

    // 예 : 회원에 따른 ROLE 지정.
    //준회원/정회원/특별회원/관리자
    //ROLE_SEMI_USER, ROLE_USER, ROLE_SPECIAL_USER, ROLE_ADMIN

    //관리자 여부 체크
    private boolean adminYn;

    //유저 상태 변경(이용가능 상태, 정지 상태)
    private String userStatus;

}
