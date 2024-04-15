package com.colsubsidio.microservicebankapi.components.exception.custom;

import org.springframework.transaction.TransactionException;

import com.colsubsidio.microservicebankapi.components.exception.error.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionExceptionCustom extends TransactionException{
    
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public TransactionExceptionCustom(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public TransactionExceptionCustom(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.TRANASCTION_ERROR.getErrMsgKey();
        this.errorCode = ErrorCode.TRANASCTION_ERROR.getErrCode();
    }
}
