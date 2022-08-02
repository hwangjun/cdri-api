package com.cdri.assignment.domain.book.exception;


import com.cdri.assignment.global.exception.BaseException;
import com.cdri.assignment.global.exception.BaseExceptionType;

public class BookException extends BaseException {
    private BaseExceptionType baseExceptionType;

    public BookException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}
