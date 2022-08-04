package com.cdri.assignment.domain.category.dao;

import com.cdri.assignment.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
