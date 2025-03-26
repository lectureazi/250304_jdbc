package com.grepp.jdbc.infra.validator;

public interface Validator<T> {
    
    void validate(T e);
    
}
