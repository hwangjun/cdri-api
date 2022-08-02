package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.Book;
import lombok.AllArgsConstructor;
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

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
    }
}
