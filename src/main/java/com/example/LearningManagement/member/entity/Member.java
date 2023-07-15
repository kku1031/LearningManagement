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
public class Member {

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

}
