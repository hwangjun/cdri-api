package com.cdri.assignment.domain.category.api;

import com.cdri.assignment.domain.category.application.CategoryService;
import com.cdri.assignment.domain.category.dto.CategoryCreateResponse;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.domain.category.dto.CategorySelectResponse;
import com.cdri.assignment.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name = "categories", description = "카테고리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 조회", description = "카테고리 전체 데이터 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "카테고리 조회 성공",
                            content = @Content(schema = @Schema(implementation = CategorySelectResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @GetMapping
    public ResponseEntity<List<CategorySelectResponse>> selectCategoryList() {
        return ResponseEntity.ok(categoryService.selectCategoryList());
    }

    @Operation(summary = "카테고리 등록", description = "카테고리 이름(unique 제약조건) request body 에 json 형태로 담아 요청",
            responses = {
                    @ApiResponse(responseCode = "201", description = "도서 검색 성공",
                            content = @Content(schema = @Schema(implementation = CategoryCreateResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @PostMapping
    public ResponseEntity<CategoryCreateResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return new ResponseEntity(categoryService.createCategory(categoryCreateRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "카테고리 ID로 해당 카테고리 조회", description = "카테고리 ID url path 에 입력하여 요청",
            responses = {
                    @ApiResponse(responseCode = "200", description = "도서 검색 성공",
                            content = @Content(schema = @Schema(implementation = CategorySelectResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategorySelectResponse> selectCategoryOne(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.selectCategoryOne(categoryId));
    }
}
