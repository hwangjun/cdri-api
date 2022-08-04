package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateResponse {
    private String author;
    private String title;
    private BookStatus bookStatus;
    private boolean isRentalAvailable;
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
