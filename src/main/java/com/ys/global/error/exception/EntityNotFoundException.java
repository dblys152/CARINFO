package com.ys.global.error.exception;

import com.ys.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
	private static final long serialVersionUID = 7596492348527040649L;

	public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
