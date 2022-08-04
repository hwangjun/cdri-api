package com.cdri.assignment.domain.book.dao;

import com.cdri.assignment.domain.book.dto.BookSearchResponse;
import com.cdri.assignment.domain.book.dto.SearchCondition;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.cdri.assignment.domain.book.domain.QBook.book;
import static com.cdri.assignment.domain.category.domain.QCategory.category;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BookSearchResponse> search(SearchCondition searchCondition) {
        return queryFactory
                .select(
                        Projections.constructor(BookSearchResponse.class,
                                book.id,
                                book.author,
                                book.title,
                                book.bookStatus,
                                book.isRentalAvailable,
                                Projections.constructor(CategorySelectResponse.class,
                                        category.id,
                                        category.name
                                )
                        ))
                .from(book)
                .join(book.category, category)
                .where(
                        categoryEq(searchCondition),
                        authorContains(searchCondition),
                        titleContains(searchCondition)
                )
                .fetch();
    }

    private BooleanExpression authorContains(SearchCondition searchCondition) {
        return hasText(searchCondition.getAuthor()) ? book.author.contains(searchCondition.getAuthor()) : null;
    }

    private BooleanExpression titleContains(SearchCondition searchCondition) {
        return hasText(searchCondition.getTitle()) ? book.title.contains(searchCondition.getTitle()) : null;
    }

    private BooleanExpression categoryEq(SearchCondition searchCondition) {
        return searchCondition.getCategory() != null ? book.category.name.eq(searchCondition.getCategory()) : null;
    }
}
