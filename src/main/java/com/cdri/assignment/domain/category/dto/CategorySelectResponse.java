package com.cdri.assignment.domain.category.dto;

import com.cdri.assignment.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "카테고리 조회 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySelectResponse {

    @Schema(description = "카테고리 ID")
    private Long id;
    @Schema(description = "카테고리 이름")
    private String name;

    public CategorySelectResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public static CategorySelectResponse fromEntity(Category category) {
        return CategorySelectResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
