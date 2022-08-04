package com.cdri.assignment.domain.book.dto;

import javax.validation.constraints.NotEmpty;

public class BookSaveRequest {
    @NotEmpty(message = "지은이를 입력해주세요.")
    private String author;
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    private String status;
    private String isRentalAvailable;
    private Long categoryId;
}
