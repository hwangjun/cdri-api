package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "도서 검색 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchResponse {

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

    @Schema(description = "카테고리정보")
    private CategorySelectResponse category;

    public static BookSearchResponse fromEntity(Book book, Category category) {
        return BookSearchResponse.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .bookStatus(book.getBookStatus())
                .isRentalAvailable(book.isRentalAvailable())
                .category(CategorySelectResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .build();
    }
}
