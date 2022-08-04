package com.cdri.assignment.domain.category.api;

import com.cdri.assignment.domain.category.application.CategoryService;
import com.cdri.assignment.domain.category.dao.CategoryRepository;
import com.cdri.assignment.domain.category.domain.Category;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.domain.category.dto.CategoryCreateResponse;
import com.cdri.assignment.domain.category.dto.CategorySearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryApiController.class)
@DisplayName("CategoryApiController 단위 테스트")
class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(new CategoryApiController(categoryService))
                        .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
                        .build();
    }

    @DisplayName("카테고리 등록 테스트")
    @Test
    public void createCategory() throws Exception {
        // given
        given(categoryService.createCategory(any()))
                .willReturn(
                        CategoryCreateResponse.fromEntity(Category.builder()
                                .id(1L)
                                .name("의학")
                                .build())
                );

        // when
        String content = "{\"name\": \"의학\"}";
        final ResultActions actions = mockMvc.perform(post("/api/categories")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("의학"))
                .andDo(print())
                .andReturn();
    }
}