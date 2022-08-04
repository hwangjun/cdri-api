package com.cdri.assignment.domain.category.application;

import com.cdri.assignment.domain.category.dao.CategoryRepository;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.domain.category.dto.CategoryCreateResponse;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("CategoryService 테스트")
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        this.categoryService = new CategoryService(categoryRepository);
    }

    @DisplayName("카테고리 등록 테스트")
    @Test
    public void createCategory() {

        // given 
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .name("문학")
                .build();

        // when
        when(categoryRepository.save(any(Category.class))).then(returnsFirstArg());
        CategoryCreateResponse response = categoryService.createCategory(request);

        // then
        assertThat(response.getName()).isEqualTo("문학");
    }

    @DisplayName("카테고리 하나 조회 테스트")
    @Test
    public void selectCategoryOne() {
        Long categoryId = 1L;
        given(categoryRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(Category.builder().id(categoryId).name("문학").build()));
        // when
        CategorySelectResponse response = categoryService.selectCategoryOne(categoryId);
        // then
        assertEquals(1L, response.getId());
        assertEquals("문학", response.getName());
    }

    @DisplayName("카테고리 전체 조회 테스트")
    @Test
    public void selectCategoryAll() {
        Long categoryId = 1L;
        Category category = Category.builder().id(categoryId).name("문학").build();

        List<Category> list = new ArrayList<>();
        list.add(category);

        given(categoryRepository.findAll()).willReturn(list);
        // when
        List<CategorySelectResponse> response = categoryService.selectCategoryList();
        // then
        assertEquals(1L, response.get(0).getId());
        assertEquals("문학", response.get(0).getName());
    }

}