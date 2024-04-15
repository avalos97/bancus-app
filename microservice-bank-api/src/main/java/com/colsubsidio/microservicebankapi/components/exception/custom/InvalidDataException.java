package com.colsubsidio.microservicebankapi.components.exception.custom;

import com.colsubsidio.microservicebankapi.components.exception.error.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public InvalidDataException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public InvalidDataException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.INVALID_DATA.getErrMsgKey();
        this.errorCode = ErrorCode.INVALID_DATA.getErrCode();
    }
}
