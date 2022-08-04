package com.cdri.assignment.domain.category.api;

import com.cdri.assignment.domain.category.application.CategoryService;
import com.cdri.assignment.domain.category.dto.CategoryCreateResponse;
import com.cdri.assignment.domain.category.dto.CategoryCreateRequest;
import com.cdri.assignment.domain.category.dto.CategorySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategorySearchResponse>> selectCategoryList() {
        return ResponseEntity.ok(categoryService.selectCategoryList());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategorySearchResponse> selectCategoryOne(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.selectCategoryOne(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryCreateResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return new ResponseEntity(categoryService.createCategory(categoryCreateRequest), HttpStatus.CREATED);
    }

}
