package com.cdri.assignment.domain.book;


import lombok.Getter;

@Getter
public enum BookStatus {

    DAMAGE("훼손"),
    LOST("분실"),
    NORMAL("정상");

    private String value;

    BookStatus(String value) {
        this.value = value;
    }
}
