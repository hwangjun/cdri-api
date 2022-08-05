package com.cdri.assignment.domain.book.application;

import com.cdri.assignment.domain.book.code.BookStatus;
import com.cdri.assignment.domain.book.dao.BookRepository;
import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.dto.*;
import com.cdri.assignment.domain.category.dao.CategoryRepository;
import com.cdri.assignment.domain.category.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@DisplayName("BookServiceTest 단위 테스트")
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        this.bookService = new BookService(bookRepository, categoryRepository);
    }

    @DisplayName("도서 ID로 도서 조회")
    @Test
    public void selectBookOne() {
        // given
        Long bookId = 1L;
        Long categoryId = 1L;

        Book book = Book.builder()
                .id(bookId)
                .author("지은이")
                .title("제목")
                .bookStatus(BookStatus.NORMAL)
                .isRentalAvailable(true)
                .build();

        Category category = Category.builder()
                .id(categoryId)
                .name("문학")
                .build();

        book.updateCategory(category);

        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(book));
        // when
        BookSelectResponse response = bookService.selectBookOne(bookId);

        // then
        assertThat(response.getId()).isEqualTo(bookId);
        assertThat(response.getCategory().getId()).isEqualTo(categoryId);
    }

}