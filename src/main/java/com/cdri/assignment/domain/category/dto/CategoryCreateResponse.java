package com.cdri.assignment.domain.category.dto;

import com.cdri.assignment.domain.category.domain.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateResponse {

    private Long id;
    private String name;

    public static CategoryCreateResponse fromEntity(Category category) {
        return CategoryCreateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
