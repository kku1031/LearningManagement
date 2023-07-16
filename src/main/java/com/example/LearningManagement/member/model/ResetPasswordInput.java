package com.example.LearningManagement.member.model;

import lombok.Data;

@Data
public class ResetPasswordInput {
    private String userId;
    private String userName;

    //url 파라미터 값과 동일
    private String id;
    private String password;

}
