package com.cdri.assignment.domain.book.dao;

import com.cdri.assignment.config.TestConfig;
import com.cdri.assignment.domain.book.code.BookStatus;
import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.book.dto.BookSearchResponse;
import com.cdri.assignment.domain.book.dto.SearchCondition;
import com.cdri.assignment.domain.category.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CategoryRepository 단위 테스트")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("도서 전체 조회")
    @Test
    public void selectBookALl() {
        // given
        // when
        List<Book> response = bookRepository.findAll();
        // then
        // data.sql 기준 첫번째 카테고리 이름
        assertEquals("권태영", response.get(0).getAuthor());
        assertEquals("너에게 해주지 못한 말들", response.get(0).getTitle());
    }

    @DisplayName("bookId로 도서 조회")
    @Test
    public void selectBookOne() {
        // given
        Long bookId = 1L;
        // when
        Optional<Book> response = bookRepository.findById(bookId);
        // then
        if (response.isPresent()) {
            assertEquals("권태영", response.get().getAuthor());
            assertEquals("너에게 해주지 못한 말들", response.get().getTitle());
        }
    }

    @DisplayName("없는 bookId로 도서 조회")
    @Test
    public void selectBookOneFail() {
        // given
        Long bookId = 99L;

        // when
        try {
            Book response = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage()).isEqualTo("해당 도서가 존재하지 않습니다.");
        }
    }

    @DisplayName("도서 상태 변경")
    @Test
    public void updateBookStatus() {
        // given
        Long bookId = 2L;
        // when
        try {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));


            System.out.println("before bookId = " + book.getId());
            System.out.println("before bookStatus = " + book.getBookStatus());
            assertThat(book.getBookStatus()).isEqualTo(BookStatus.NORMAL);

            book.updateStatus(BookStatus.DAMAGE);

            bookRepository.save(book);

            System.out.println("after bookId = " + book.getId());
            System.out.println("after bookStatus = " + book.getBookStatus());
            assertThat(book.getBookStatus()).isEqualTo(BookStatus.DAMAGE);

        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage()).isEqualTo("해당 도서가 존재하지 않습니다.");
        }
    }

    @DisplayName("도서 카테고리 변경")
    @Test
    public void updateBookCategory() {
        // given
        Long bookId = 2L;
        Long categoryId = 2L;
        Category category = Category.builder()
                .id(categoryId)
                .build();

        // when
        try {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));

            System.out.println("before bookId = " + book.getId());
            System.out.println("before categoryId = " + book.getCategory().getId());


            book.updateCategory(category);

            bookRepository.save(book);

            System.out.println("after bookId = " + book.getId());
            System.out.println("after categoryId = " + book.getCategory().getId());

            //then
            assertThat(book.getCategory().getId()).isEqualTo(categoryId);

        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage()).isEqualTo("해당 도서가 존재하지 않습니다.");
        }
    }

    @DisplayName("제목으로 도서 검색")
    @Test
    public void searchBookByTitle() {
        // given
        String title = "너에게 해주지 못한 말들";
        SearchCondition condition = new SearchCondition();
        condition.setTitle(title);

        // when
        List<BookSearchResponse> book = bookRepository.search(condition);

        //then
        assertThat(book.get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("지은이로 도서 검색")
    @Test
    public void searchBookByAuthor() {
        // given
        String author = "권태영";
        SearchCondition condition = new SearchCondition();
        condition.setAuthor(author);

        // when
        List<BookSearchResponse> book = bookRepository.search(condition);

        //then
        assertThat(book.get(0).getAuthor()).isEqualTo(author);
    }

    @DisplayName("카테고리 이름로 도서 검색")
    @Test
    public void searchBookByCategory() {
        // given
        String categoryName = "문학";
        SearchCondition condition = new SearchCondition();
        condition.setCategory(categoryName);

        // when
        List<BookSearchResponse> book = bookRepository.search(condition);

        //then
        assertThat(book.get(0).getCategory().getName()).isEqualTo(categoryName);
    }
}