package com.cdri.assignment.domain.book.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Schema(description = "도서등록 요청 DTO")
@Getter
@Setter
public class BookCreateRequest {

    @Schema(example = "홍길동", description = "지은이를 입력해주세요.")
    @Parameter(name = "지은이" , required = true)
    @NotEmpty(message = "지은이를 입력해주세요.")
    private String author;

    @Schema(example = "홍길동전", description = "제목을 입력해주세요.")
    @Parameter(name = "제목", required = true)
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @Schema(example = "1", description = "카테고리번호 입력해주세요.")
    @Parameter(name = "카테고리", required = true)
    @NotNull(message = "카테고리 등록은 필수 입니다." )
    private Long categoryId;
}
