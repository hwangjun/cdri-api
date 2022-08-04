package com.cdri.assignment.domain.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description = "도서검색 조건 요청 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    @Schema(description = "지은이 문자를 포함하는(contains) 조건")
    private String author;

    @Schema(description = "제목 문자를 포함하는(contains) 조건")
    private String title;

    @Schema(description = "카테고리 이름 문자와 같은(equals) 조건")
    private String category;

}
