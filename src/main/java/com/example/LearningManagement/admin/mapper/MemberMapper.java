package com.example.LearningManagement.admin.mapper;

import com.example.LearningManagement.admin.dto.MemberDto;
import com.example.LearningManagement.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);

    List<MemberDto> selectList(MemberParam parameter);

}
