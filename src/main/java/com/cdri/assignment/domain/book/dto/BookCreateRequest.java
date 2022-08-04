package com.cdri.assignment.domain.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class BookCreateRequest {

    @NotEmpty(message = "지은이를 입력해주세요.")
    private String author;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotNull(message = "카테고리 등록은 필수 입니다." )
    private Long categoryId;
}
