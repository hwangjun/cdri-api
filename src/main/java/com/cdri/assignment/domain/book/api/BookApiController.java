package com.cdri.assignment.domain.book.api;

import com.cdri.assignment.domain.book.application.BookService;
import com.cdri.assignment.domain.book.dto.*;
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

import javax.validation.Valid;
import java.util.List;


@Tag(name = "books", description = "도서 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService bookService;

    @Operation(summary = "도서 검색", description = "지은이, 제목, 카테고리 이름을 통한 도서 검색(검색조건이 없으면 도서 전체 조회)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "도서 검색 성공",
                            content = @Content(schema = @Schema(implementation = BookSearchResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @GetMapping
    public ResponseEntity<List<BookSearchResponse>> searchBook(SearchCondition searchCondition) {
        return ResponseEntity.ok(bookService.searchBook(searchCondition));
    }

    @Operation(summary = "도서 등록", description = "도서 등록시 카테고리 ID도 함께 전달하여 등록(신규 도서는 카테고리 필수)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "도서 둥록 성공",
                            content = @Content(schema = @Schema(implementation = BookCreateResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PostMapping
    public ResponseEntity<BookCreateResponse> createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return new ResponseEntity(bookService.createBook(bookCreateRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "도서 ID로 해당 도서 조회", description = "도서 ID로 데이터 하나 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "도서 조회 성공",
                            content = @Content(schema = @Schema(implementation = BookSelectResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @GetMapping("/{bookId}")
    public ResponseEntity<BookSelectResponse> selectBookOne(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.selectBookOne(bookId));
    }

    @Operation(summary = "도서 수정", description = "도서 카테고리, 상태(훼손, 분실, 정상) 수정 \n" +
            "1. bookStatus 값만 보내면 도서상태 변경 \n" +
            "2. categoryId 값만 보내면 카테고리 변경",
            responses = {
                    @ApiResponse(responseCode = "200", description = "도서 조회 성공",
                            content = @Content(schema = @Schema(implementation = BookSelectResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @PatchMapping("/{bookId}")
    public ResponseEntity<BookUpdateResponse> updateBook(@PathVariable Long bookId,
                                                         @RequestBody BookUpdateRequest bookUpdateRequest) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookUpdateRequest));
    }

}
