package com.cdri.assignment.domain.category.dto;

import com.cdri.assignment.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "카테고리 등록 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateResponse {

    @Schema(description = "카테고리 ID")
    private Long id;
    @Schema(description = "카테고리 이름")
    private String name;

    public static CategoryCreateResponse fromEntity(Category category) {
        return CategoryCreateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
