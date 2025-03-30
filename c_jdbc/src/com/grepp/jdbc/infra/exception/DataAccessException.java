package com.grepp.jdbc.infra.exception;

public class DataAccessException extends CommonException {
    
    public DataAccessException(String message) {
        super(message);
    }
    
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
