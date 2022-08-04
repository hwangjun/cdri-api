package com.cdri.assignment.domain.category.application;

import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dao.CategoryRepository;
import com.cdri.assignment.domain.category.dto.CategoryCreateResponse;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategorySelectResponse> selectCategoryList() {
        return categoryRepository.findAll()
                .stream().map(CategorySelectResponse::new)
                .collect(Collectors.toList());
    }

    public CategorySelectResponse selectCategoryOne(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        return CategorySelectResponse.fromEntity(category);
    }

    public CategoryCreateResponse createCategory(CategoryCreateRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();

        Category createCategory = categoryRepository.save(category);
        return CategoryCreateResponse.fromEntity(createCategory);
    }
}
