package com.colsubsidio.microservicebankapi.components.exception.error;

/**
 * An enumeration of error codes and associated message keys for validation
 * errors
 */
public enum ErrorCode {

    // Internal Errors: 1 to 0999
    GENERIC_ERROR("PACKT-0001", "The system is unable to complete the request. Contact system support."),
    CONSTRAINT_VIOLATION("PACKT-0002", "Validation failed."),
    ILLEGAL_ARGUMENT_EXCEPTION("PACKT-0003", "Invalid data passed."),
    RESOURCE_NOT_FOUND("PACKT-0004", "Requested resource not found."),
    RECORD_NOT_FOUND("PACKT-0005", "Record not found - "),
    GENERIC_ALREADY_EXISTS("PACKT-0006", "Already exists."),
    INVALID_DATA("PACKT-0007", "Invalid data - "),
    TRANASCTION_ERROR("PACKT-0008", "Error in the requested transaction - ");


    private String errCode;
    private String errMsgKey;

    private ErrorCode(final String errCode, final String errMsgKey) {
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }

    /**
     * @return the errCode
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * @return the errMsgKey
     */
    public String getErrMsgKey() {
        return errMsgKey;
    }
}
