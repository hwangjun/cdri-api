package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dto.CategorySearchResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchResponse {
    private Long id;
    private String author;
    private String title;
    private BookStatus bookStatus;
    private boolean isRentalAvailable;
    private CategorySearchResponse category;

    public static BookSearchResponse fromEntity(Book book, Category category) {
        return BookSearchResponse.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .bookStatus(book.getBookStatus())
                .isRentalAvailable(book.isRentalAvailable())
                .category(CategorySearchResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .build();
    }
}
