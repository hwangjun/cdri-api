package com.cdri.assignment.domain.book.application;

import com.cdri.assignment.domain.book.Book;
import com.cdri.assignment.domain.book.dao.BookRepository;
import com.cdri.assignment.domain.book.dto.BookResponseDto;
import com.cdri.assignment.domain.book.exception.BookExceptionType;
import com.cdri.assignment.domain.book.exception.BookException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAll().stream().map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    public BookResponseDto getBookOne(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException(BookExceptionType.NOT_FOUND_BOOK));

        return modelMapper.map(book, BookResponseDto.class);
    }

    public List<BookResponseDto> searchBooks(String author, String title) {
        return bookRepository.searchBooks(author, title);
    }
}
