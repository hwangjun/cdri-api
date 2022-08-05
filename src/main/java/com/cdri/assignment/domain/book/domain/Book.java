package com.cdri.assignment.domain.book.domain;


import com.cdri.assignment.domain.book.BookStatus;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.global.common.BaseEntity;
import com.cdri.assignment.global.util.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 대여 가능 여부(Y/N)
    @Convert(converter = BooleanToYNConverter.class)
    @Column(length = 1)
    private boolean isRentalAvailable;

    @Builder
    public Book(Long id, String author, String title, BookStatus bookStatus, boolean isRentalAvailable) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.bookStatus = bookStatus;
        this.isRentalAvailable = isRentalAvailable;
    }

    // 카테고리 설정
    public void updateCategory(Category category) {
        // 이미 카테고리가 있을 경우 관계를 제거한다.
        if (this.category != null) {
            this.category.getBooks().remove(this);
        }

        this.category = category;

        if (category != null) {
            category.getBooks().add(this);
        }
    }

    public void updateStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;

        this.isRentalAvailable = bookStatus == BookStatus.NORMAL;
    }
}
