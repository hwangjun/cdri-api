package com.cdri.assignment.domain.book.api;

import com.cdri.assignment.domain.book.application.BookService;
import com.cdri.assignment.domain.book.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookApiController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookResponseDto> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/books/{bookId}")
    public BookResponseDto getBookOne(@PathVariable Long bookId) {
        return bookService.getBookOne(bookId);
    }

    @GetMapping("/books/search")
    public List<BookResponseDto> searchBooks(@RequestParam String author,
                                             @RequestParam String title) {
        return bookService.searchBooks(author, title);
    }


}
