package com.cdri.assignment.domain.book.dao;


import com.cdri.assignment.domain.book.Book;
import com.cdri.assignment.domain.book.dto.BookResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    // 메소드명 쿼리
    List<BookResponseDto> findBooksByAuthorContainsAndTitleContains(String author, String title);

    // 어노테이션 jpql
    @Query("select b from Book b where b.author like %:author% and b.title like %:title%")
    List<BookResponseDto> searchBooksJpql(@Param("author") String author, @Param("title") String title);

}