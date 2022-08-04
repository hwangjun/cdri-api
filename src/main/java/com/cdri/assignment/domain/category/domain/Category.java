package com.cdri.assignment.domain.category.domain;

import com.cdri.assignment.domain.book.domain.Book;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy="category")
    private List<Book> books = new ArrayList<>();

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category toEntity(CategoryCreateRequest categoryRequestDto) {
        this.name = categoryRequestDto.getName();

        return this;
    }
}
