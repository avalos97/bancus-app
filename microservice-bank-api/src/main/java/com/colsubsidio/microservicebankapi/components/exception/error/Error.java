package com.colsubsidio.microservicebankapi.components.exception.error;

import java.time.Instant;

import org.apache.logging.log4j.util.Strings;

import lombok.Getter;
import lombok.Setter;

/**
 * Complex type that contains error details for a REST API calls.
 */
@Getter
@Setter
public class Error {

    /**
     * Application error code, which is different from HTTP error code.
     */
    private String errorCode;

    /**
     * Short, human-readable summary of the problem.
     */
    private String message;

    /**
     * HTTP status code for this occurrence of the problem, set by the origin
     * server.
     */
    private Integer status;

    /**
     * Url of request that produced the error.
     */
    private String url = "Not available";

    /**
     * Method of request that produced the error.
     */
    private String reqMethod = "Not available";

    /**
     * Timestamp
     */
    private Instant timestamp;

    public Error setUrl(String url) {
        if (Strings.isNotBlank(url)) {
            this.url = url;
        }
        return this;
    }

    public Error setReqMethod(String method) {
        if (Strings.isNotBlank(method)) {
            this.reqMethod = method;
        }
        return this;
    }

    public Error setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
