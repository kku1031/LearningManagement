package com.example.LearningManagement.admin.model;

import lombok.Data;

@Data
public class MemberInput {

    //회원 상태
    String userId;
    String userStatus;

    //회원 비밀번호 초기화
    String password;
    

}
