package com.colsubsidio.microservicebankapi.components.exception.custom;

import com.colsubsidio.microservicebankapi.components.exception.error.ErrorCode;

import lombok.Getter;
import lombok.Setter;

/**
 * Generic exception thrown when there is an attempt to create an entity with a
 * unique code that already exists.
 */
@Getter
@Setter
public class GenericAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public GenericAlreadyExistsException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public GenericAlreadyExistsException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.GENERIC_ALREADY_EXISTS.getErrMsgKey();
        this.errorCode = ErrorCode.GENERIC_ALREADY_EXISTS.getErrCode();
    }
}
