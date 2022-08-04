package com.cdri.assignment.domain.book.dao;


import com.cdri.assignment.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    /*

    //  메소드명 쿼리 사용안함
    List<BookResponseDto> findAllByAuthorContaining(String author);
    List<BookResponseDto> findAllByTitleContaining(String title);
    List<BookResponseDto> findBooksByAuthorContainsAndTitleContains(String author, String title);
    List<BookResponseDto> findBooksByCategory(Category category);
    List<BookResponseDto> findAllByCategory(Category category);

    // 어노테이션 jpql
    @Query("select b from Book b where b.author like %:author% and b.title like %:title%")
    List<BookResponseDto> searchBooksJpql(@Param("author") String author, @Param("title") String title);

    */
}