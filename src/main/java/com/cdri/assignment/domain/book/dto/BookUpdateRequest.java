package com.cdri.assignment.domain.book.dto;

import com.cdri.assignment.domain.book.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "도서 수정(상태, 카테고리 변경) 요청 DTO")
@Getter
@Setter
public class BookUpdateRequest {

    @Schema(description = "도서상태(훼손, 분실, 정상)")
    private BookStatus bookStatus;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

}
