package com.cdri.assignment.domain.book.dao;

import com.cdri.assignment.domain.book.dto.BookResponseDto;

import java.util.List;

public interface BookRepositoryCustom {
    List<BookResponseDto> searchBooks(String author, String title);
}
