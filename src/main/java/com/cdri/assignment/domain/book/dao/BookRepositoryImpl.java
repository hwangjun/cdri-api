package com.cdri.assignment.domain.book.dao;

import com.cdri.assignment.domain.book.dto.BookResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.cdri.assignment.domain.book.QBook.book;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BookResponseDto> searchBooks(String author, String title) {
        return queryFactory
                .select(Projections.constructor(BookResponseDto.class,
                        book.id,
                        book.author,
                        book.title
                        ))
                .from(book)
                .where(authorEq(author),
                        titleEq(title))
                .fetch();
    }

    private BooleanExpression authorEq(String author) {
        return hasText(author) ? book.author.eq(author) : null;
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? book.title.eq(title) : null;
    }
}
