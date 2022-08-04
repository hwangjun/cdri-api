package com.cdri.assignment.domain.category.dto;


import com.cdri.assignment.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description = "카테고리 등록 요청 DTO")
@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateRequest {

    @Parameter(name = "카테고리 이름", required = true)
    @Schema(example = "요리", description = "카테고리 이름")
    private String name;

    @Builder
    public CategoryCreateRequest(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder().name(name).build();
    }

}
