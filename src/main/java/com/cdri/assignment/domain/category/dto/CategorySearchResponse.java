package com.cdri.assignment.domain.category.dto;

import com.cdri.assignment.domain.category.domain.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySearchResponse {

    private Long id;
    private String name;

    public CategorySearchResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public static CategorySearchResponse fromEntity(Category category) {
        return CategorySearchResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
