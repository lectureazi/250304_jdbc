package com.grepp.jdbc.infra.exception;

public class JdbcInitializeException extends RuntimeException {
    
    public JdbcInitializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
