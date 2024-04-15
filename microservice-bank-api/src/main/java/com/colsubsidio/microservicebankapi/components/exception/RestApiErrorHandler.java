package com.colsubsidio.microservicebankapi.components.exception;

import java.time.Instant;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.colsubsidio.microservicebankapi.components.exception.custom.GenericAlreadyExistsException;
import com.colsubsidio.microservicebankapi.components.exception.custom.InvalidDataException;
import com.colsubsidio.microservicebankapi.components.exception.custom.RecordNotFoundException;
import com.colsubsidio.microservicebankapi.components.exception.custom.ResourceNotFoundException;
import com.colsubsidio.microservicebankapi.components.exception.custom.TransactionExceptionCustom;
import com.colsubsidio.microservicebankapi.components.exception.error.ErrorCode;
import com.colsubsidio.microservicebankapi.components.exception.error.ErrorUtils;
import com.colsubsidio.microservicebankapi.components.exception.error.Error;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestApiErrorHandler {

        private static final Logger log = LoggerFactory.getLogger(RestApiErrorHandler.class);

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex,
                        Locale locale) {
                Error error = ErrorUtils
                                .createError(ErrorCode.GENERIC_ERROR.getErrMsgKey(),
                                                ErrorCode.GENERIC_ERROR.getErrCode(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Error> handleHIllegalArgumentException(
                        HttpServletRequest request,
                        IllegalArgumentException ex,
                        Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s",
                                                                ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrCode(),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request,
                        ResourceNotFoundException ex, Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ex.getErrorCode(),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error("ResourceNotFoundException :: request.getMethod(): " + request.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidDataException.class)
        public ResponseEntity<Error> handleInvalidDataException(HttpServletRequest request,
                        InvalidDataException ex, Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.INVALID_DATA.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ex.getErrorCode(),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error("InvalidDataException :: request.getMethod(): " + request.getMethod());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RecordNotFoundException.class)
        public ResponseEntity<Error> handleItemNotFoundException(HttpServletRequest request,
                        RecordNotFoundException ex, Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.RECORD_NOT_FOUND.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ex.getErrorCode(),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error("ItemNotFoundException :: request.getMethod(): " + request.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(GenericAlreadyExistsException.class)
        public ResponseEntity<Error> handleGenericAlreadyExistsException(HttpServletRequest request,
                        GenericAlreadyExistsException ex, Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.GENERIC_ALREADY_EXISTS.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ex.getErrorCode(),
                                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error("GenericAlreadyExistsException :: request.getMethod(): " + request.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(TransactionExceptionCustom.class)
        public ResponseEntity<Error> handleTransactionException(HttpServletRequest request,
                        TransactionExceptionCustom ex, Locale locale) {
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.TRANASCTION_ERROR.getErrMsgKey(),
                                                                ex.getMessage()),
                                                ex.getErrorCode(),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error(String.format("TransactionExceptionCustom :: request.getMethod(): %s", request.getMethod()));
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Error> handleInvalidDataException(HttpServletRequest request,
                        MethodArgumentNotValidException ex, Locale locale) {
                String errorMessage = null;
                for (Object error : ex.getBindingResult().getAllErrors()) {
                        if (error instanceof FieldError) {
                                errorMessage = ((FieldError) error).getDefaultMessage();
                                if (errorMessage != null) {
                                        break;
                                }
                        }
                }
                Error error = ErrorUtils
                                .createError(
                                                String.format("%s %s", ErrorCode.INVALID_DATA.getErrMsgKey(),
                                                                errorMessage),
                                                ErrorCode.INVALID_DATA.getErrCode(),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(request.getRequestURL().toString())
                                .setReqMethod(request.getMethod())
                                .setTimestamp(Instant.now());
                log.error("InvalidDataException :: request.getMethod(): " + request.getMethod());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
}
