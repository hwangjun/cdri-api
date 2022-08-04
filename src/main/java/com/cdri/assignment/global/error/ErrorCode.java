package com.cdri.assignment.global.error;

public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "COMMON_001", "유효하지 않은 값입니다."),
    METHOD_NOT_ALLOWED(405, "COMMON_002", "허용되지 않은 HTTP 메소드 입니다."),
    HANDLE_ACCESS_DENIED(403, "COMMON_003", "접근 권한이 없습니다."),

    // Standard
    ILLEGAL_STATE(400, "STANDARD_001", "요청 파라미터가 적절하지 않습니다."),
    ILLEGAL_ARGUMENT(400, "STANDARD_002", "요청 파라미터가 적절하지 않습니다."),

    // Exception
    EXCEPTION(500, "EXCEPTION", "예외가 발생했습니다.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    protected String getCode() { return code; }
    protected String getMessage() { return message; }
    protected int getStatus() { return status; }
}
