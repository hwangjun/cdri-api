package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.code.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "도서등록 응답 DTO")
@Getter
@Setter
@Builder
public class BookCreateResponse {

    @Schema(description = "지은이")
    private String author;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "도서상태(훼손, 분실, 정상)")
    private BookStatus bookStatus;

    @Schema(description = "대여가능여부")
    private boolean isRentalAvailable;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    public static BookCreateResponse fromEntity(Book book) {
        return BookCreateResponse.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .bookStatus(book.getBookStatus())
                .isRentalAvailable(book.isRentalAvailable())
                .categoryId(book.getCategory().getId())
                .build();
    }
}
