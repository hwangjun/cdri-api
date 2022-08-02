package com.cdri.assignment.domain.book.exception;

import com.cdri.assignment.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum BookExceptionType implements BaseExceptionType {

    NOT_FOUND_BOOK(800, HttpStatus.NOT_FOUND, "해당 책이 존재하지 않습니다.");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;


    BookExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
