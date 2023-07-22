package com.example.LearningManagement.admin.service;

import com.example.LearningManagement.admin.dto.CategoryDto;
import com.example.LearningManagement.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {


    //전체 카테고리 가져오기
    List<CategoryDto> list();

    //카테고리 신규 추가
    boolean add(String categoryName);

    //카테고리 수정
    boolean update(CategoryInput parameter);

    //카테고리 삭제
    boolean del(long id);

}
