package com.ys.global.error.exception;

import com.ys.global.error.ErrorCode;

public class InvalidValueException extends BusinessException {
	private static final long serialVersionUID = -6755230684833837907L;

	public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
