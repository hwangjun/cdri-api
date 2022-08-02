package com.cdri.assignment.domain.category;

import com.cdri.assignment.domain.book.Book;
import com.cdri.assignment.global.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy="category")
    private List<Book> books = new ArrayList<>();
}
