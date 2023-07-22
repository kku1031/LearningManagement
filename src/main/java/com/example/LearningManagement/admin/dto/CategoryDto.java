package com.example.LearningManagement.admin.dto;

import com.example.LearningManagement.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String categoryName;
    int sortValue;
    boolean usingYn;

    public static List<CategoryDto> of (List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x : categories) {
                categoryList.add(of(x));
            }
            return categoryList;
        }
        return null;
    }

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }
}
