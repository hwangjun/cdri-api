package com.cdri.assignment.domain.book.api;

import com.cdri.assignment.domain.book.application.BookService;
import com.cdri.assignment.domain.book.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> selectBookOne(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.selectBookOne(bookId));
    }

    @GetMapping
    public ResponseEntity<List<BookSearchResponse>> searchBook(SearchCondition searchCondition) {
        return ResponseEntity.ok(bookService.searchBook(searchCondition));
    }

    @PostMapping
    public ResponseEntity<BookCreateResponse> createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return new ResponseEntity(bookService.createBook(bookCreateRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<BookUpdateResponse> updateBook(@PathVariable Long bookId,
                                                         @RequestBody BookUpdateRequest bookUpdateRequest) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookUpdateRequest));
    }

}
