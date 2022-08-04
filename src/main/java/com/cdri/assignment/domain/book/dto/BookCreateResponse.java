package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookCreateResponse {

    private String author;
    private String title;
    private BookStatus bookStatus;
    private boolean isRentalAvailable;
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
