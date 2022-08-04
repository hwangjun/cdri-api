package com.cdri.assignment.domain.book.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("도서 등록 정상 데이터 테스트")
    @Test
    public void createBook() throws Exception {
        String content = "{\"author\": \"테스트1\", \"title\": \"제목 테스트1\", \"categoryId\": \"1\"}";

        mockMvc.perform(post("/api/books")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
    }

    @DisplayName("도서 등록 카테고리 제외 데이터 테스트")
    @Test
    public void createBookNoCategory() throws Exception {
        String content = "{\"author\": \"테스트1\", \"title\": \"제목 테스트1\"}";

        mockMvc.perform(post("/api/books")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].reason").value("카테고리 등록은 필수 입니다."))
                .andDo(print())
                .andReturn();
    }


    @DisplayName("도서 전체 조회 테스트")
    @Test
    public void findBookAll() throws Exception {
        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @DisplayName("지은이로 도서 검색 테스트")
    @Test
    public void searchConditionAuthorContains() throws Exception {
        mockMvc.perform(get("/api/books")
                        .param("author","권태영")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].author").value("권태영"))
                .andDo(print())
                .andReturn();
    }

    @DisplayName("제목으로 도서 검색 테스트")
    @Test
    public void searchConditionTitleContains() throws Exception {
        mockMvc.perform(get("/api/books")
                        .param("title","너에게 해주지 못한 말들")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("너에게 해주지 못한 말들"))
                .andDo(print())
                .andReturn();
    }

    @DisplayName("카테고리로 도서 검색 테스트")
    @Test
    public void searchConditionCategoryEquals() throws Exception {
        mockMvc.perform(get("/api/books")
                        .param("category","문학")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].category.name").value("문학"))
                .andDo(print())
                .andReturn();
    }

    @DisplayName("도서 카테고리 변경 테스트")
    @Test
    public void updateBookCategory() throws Exception {
        String content = "{\"categoryId\": \"2\"}";

        mockMvc.perform(patch("/api/books/2")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value("2"))
                .andDo(print())
                .andReturn();
    }

    @DisplayName("도서 분실 상태로 변경 테스트")
    @Test
    public void updateBookStatus() throws Exception {
        String content = "{\"bookStatus\": \"LOST\"}";

        mockMvc.perform(patch("/api/books/2")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rentalAvailable").value(false))
                .andExpect(jsonPath("$.bookStatus").value("LOST"))
                .andDo(print())
                .andReturn();
    }
}