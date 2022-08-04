package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description = "도서 조회 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
public class BookSelectResponse {

    @Schema(description = "도서 ID")
    private Long id;

    @Schema(description = "지은이")
    private String author;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "도서상태(훼손, 분실, 정상)")
    private BookStatus bookStatus;

    @Schema(description = "대여가능여부")
    private boolean isRentalAvailable;

    @Schema(description = "카테고리 정보")
    private CategorySelectResponse category;

    public BookSelectResponse(Long id, String author, String title, CategorySelectResponse category) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.category = category;
    }

    public BookSelectResponse(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
    }

    @Builder
    public BookSelectResponse(Long id, String author, String title, BookStatus bookStatus, boolean isRentalAvailable, CategorySelectResponse category) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.bookStatus = bookStatus;
        this.isRentalAvailable = isRentalAvailable;
        this.category = category;
    }

    public static BookSelectResponse fromEntity(Book book) {
        return BookSelectResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .bookStatus(book.getBookStatus())
                .isRentalAvailable(book.isRentalAvailable())
                .category(CategorySelectResponse.builder()
                        .id(book.getCategory().getId())
                        .name(book.getCategory().getName())
                        .build())
                .build();
    }
}
