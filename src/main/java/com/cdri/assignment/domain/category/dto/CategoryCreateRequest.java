package com.cdri.assignment.domain.category.dto;


import com.cdri.assignment.domain.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateRequest {

    private String name;

    @Builder
    public CategoryCreateRequest(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder().name(name).build();
    }

}
