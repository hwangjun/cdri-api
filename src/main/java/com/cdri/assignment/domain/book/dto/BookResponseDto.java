package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.category.dto.CategorySearchResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String author;
    private String title;
    private BookStatus bookStatus;
    private boolean isRentalAvailable;
    private CategorySearchResponse category;


    public BookResponseDto(Long id, String author, String title, CategorySearchResponse category) {
        this.id = id;
        this.author = author;
        this.title = title;

        this.category = category;
    }

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
    }

}
