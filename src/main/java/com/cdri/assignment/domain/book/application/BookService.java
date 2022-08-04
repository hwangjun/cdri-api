package com.cdri.assignment.domain.book.application;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.book.dao.BookRepository;
import com.cdri.assignment.domain.book.dto.*;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dao.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookResponseDto selectBookOne(Long bookId) {
        // 도서 존재하는지 확인
        Book book = bookExistenceCheck(bookId);
        return new BookResponseDto(book);
    }

    public List<BookSearchResponse> searchBook(SearchCondition searchCondition) {
        return bookRepository.search(searchCondition);
    }

    @Transactional
    public BookCreateResponse createBook(BookCreateRequest bookCreateRequest) {
        // 카테고리가 존재하는지 확인
        Category category = categoryExistenceCheck(bookCreateRequest.getCategoryId());

        Book book = Book.builder()
                .author(bookCreateRequest.getAuthor())
                .title(bookCreateRequest.getTitle())
                .bookStatus(BookStatus.NORMAL) // 초기 도서상태 NORMAL(정상)
                .isRentalAvailable(true)  // 초기 대여가능여부(가능)
                .build();

        book.updateCategory(category); // 카테고리 추가

        Book createBook = bookRepository.save(book);
        return BookCreateResponse.fromEntity(createBook);
    }

    private Category categoryExistenceCheck(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
    }

    @Transactional
    public BookUpdateResponse updateBook(Long bookId, BookUpdateRequest bookUpdateRequest) {
        // 도서 존재하는지 확인
        Book book = bookExistenceCheck(bookId);
        BookStatus bookStatus = bookUpdateRequest.getBookStatus();
        Long categoryId = bookUpdateRequest.getCategoryId();

        log.debug("categoryId {}", categoryId);
        log.debug("bookStatus {}", bookStatus);

        if (bookStatus != null) {
            book.updateStatus(bookStatus);
        }

        if (categoryId != null) {
            // 카테고리 존재하는지 확인
            Category category = categoryExistenceCheck(categoryId);
            //도서 카테고리 수정
            book.updateCategory(category);
        }

        Book updateBook = bookRepository.save(book);
        return BookUpdateResponse.fromEntity(updateBook);
    }

    private Book bookExistenceCheck(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
    }
}
