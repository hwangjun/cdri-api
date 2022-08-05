package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.code.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "도서 수정 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateResponse {

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

    public static BookUpdateResponse fromEntity(Book updateBook) {
        return BookUpdateResponse.builder()
                .author(updateBook.getAuthor())
                .title(updateBook.getTitle())
                .bookStatus(updateBook.getBookStatus())
                .isRentalAvailable(updateBook.isRentalAvailable())
                .categoryId(updateBook.getCategory().getId())
                .build();
    }
}
