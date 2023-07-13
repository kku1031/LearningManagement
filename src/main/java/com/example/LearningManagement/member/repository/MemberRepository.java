package com.example.LearningManagement.member.repository;

import com.example.LearningManagement.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {



}
