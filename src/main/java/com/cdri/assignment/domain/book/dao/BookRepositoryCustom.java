package com.cdri.assignment.domain.book.dao;

import com.cdri.assignment.domain.book.dto.BookSearchResponse;
import com.cdri.assignment.domain.book.dto.SearchCondition;

import java.util.List;

public interface BookRepositoryCustom {
    List<BookSearchResponse> search(SearchCondition condition);
}
