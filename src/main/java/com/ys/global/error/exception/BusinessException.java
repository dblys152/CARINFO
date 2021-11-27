package com.ys.global.error.exception;

import com.ys.global.error.ErrorCode;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -4716581085091098792L;

	private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}