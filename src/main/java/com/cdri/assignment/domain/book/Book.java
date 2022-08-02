package com.cdri.assignment.domain.book;


import com.cdri.assignment.domain.category.Category;
import com.cdri.assignment.global.common.BaseEntity;
import com.cdri.assignment.global.exception.BaseException;
import com.cdri.assignment.global.util.BooleanToYNConverter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    // 대여 가능 여부
    @Convert(converter = BooleanToYNConverter.class)
    private boolean isRentalAvailable;

    public void setCategory(Category category) {
        // 이미 카테고리가 있을 경우 관계를 제거한다.
        if( this.category != null ) {
            this.category.getBooks().remove(this);
        }

        this.category = category;

        if( category != null ) {
            category.getBooks().add(this);
        }
    }

}
