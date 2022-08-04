package com.cdri.assignment.global.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "에러 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    @Schema(description = "에러 코드")
    private String code;
    @Schema(description = "에러 메시지")
    private String message;
    @Schema(description = "에러 상태")
    private int status;
    private List<CustomFieldError> errors;


    @Schema(description = "필드 에러 응답 DTO")
    public static class CustomFieldError{
        @Schema(description = "필드 이름")
        private String field;
        @Schema(description = "필드 에러 값")
        private String value;
        @Schema(description = "필드 에러 이유")
        private String reason;

        private CustomFieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private CustomFieldError(FieldError fieldError){
            this.field = fieldError.getField();
            this.value = fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : "";
            this.reason = fieldError.getDefaultMessage();
        }

        public String getField() { return field; }
        public String getValue() { return value; }
        public String getReason() { return reason; }
    }

    private void setErrorCode(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }

    private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        setErrorCode(errorCode);
        this.errors = errors.stream().map(CustomFieldError::new).collect(Collectors.toList());
    }

    private ErrorResponse(ErrorCode errorCode, String exceptionMessage) {
        setErrorCode(errorCode);
        this.errors = List.of(new CustomFieldError("", "", exceptionMessage));
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(errorCode, Collections.emptyList());
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult){
        return new ErrorResponse(errorCode, bindingResult.getFieldErrors());
    }

    public static ErrorResponse of(ErrorCode errorCode, String exceptionMessage){
        return new ErrorResponse(errorCode, exceptionMessage);
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public int getStatus() { return status; }
    public List<CustomFieldError> getErrors() { return errors; }
}
