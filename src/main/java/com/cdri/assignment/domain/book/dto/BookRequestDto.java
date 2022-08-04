package com.cdri.assignment.domain.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {

    private Long id;
    @NotEmpty(message = "지은이를 입력해주세요.")
    private String author;
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    private String status;
    private String isRentalAvailable;
    private Long categoryId;

    @Builder
    public BookRequestDto(String author, String title, String status) {
        this.author = author;
        this.title = title;
        this.status = status;
    }
}
