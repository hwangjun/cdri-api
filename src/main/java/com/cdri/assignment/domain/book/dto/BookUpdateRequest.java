package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest {

    private BookStatus bookStatus;
    private Long categoryId;

}
