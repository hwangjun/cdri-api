package com.cdri.assignment.domain.category.dao;

import com.cdri.assignment.config.TestConfig;
import com.cdri.assignment.domain.category.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;


    @DisplayName("카테고리 전체 조회 테스트")
    @Test
    public void selectCategoryAll() {

        // given
        // when
        List<Category> response = categoryRepository.findAll();
         // then
        // data.sql 기준 첫번째 카테고리 이름
        assertEquals("문학", response.get(0).getName());
    }



    @DisplayName("카테고리 생성 테스트")
    @Test
    public void createCategory() {

        // given
        String categoryName = "11과학";
        Category category = Category.builder()
                .name(categoryName)
                .build();

        //when
        Category response = categoryRepository.save(category);

        // then
        assertEquals(categoryName, response.getName());
    }

    @DisplayName("카테고리 동일한 카테고리명으로 생성시(unique 제약조건) 실패하는지 테스트")
    @Test
    public void createCategoryFail() {

        // given
        String categoryName = "문학";
        Category category = Category.builder()
                .name(categoryName)
                .build();

        // when && then
        assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(category));

    }
}