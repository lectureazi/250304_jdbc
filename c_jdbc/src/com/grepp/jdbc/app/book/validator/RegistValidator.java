package com.grepp.jdbc.app.book.validator;

import com.grepp.jdbc.app.book.code.Category;
import com.grepp.jdbc.app.book.dto.form.RegistForm;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;

public class RegistValidator implements Validator<RegistForm> {
    
    @Override
    public void validate(RegistForm form) {
        
        if (form.getTitle() == null || form.getTitle().isBlank()) {
            throw new ValidException("도서명은 공백일 수 없습니다.");
        }
        
        if (form.getIsbn() == null || form.getIsbn().isBlank()) {
            throw new ValidException("ISBN은 공백일 수 없습니다.");
        }
        
        if (form.getAuthor() == null || form.getAuthor().isBlank()) {
            throw new ValidException("작가명은 공백일 수 없습니다.");
        }
        
        if (form.getCategory() == null || form.getCategory().isBlank()) {
            throw new ValidException("Category는 공백일 수 없습니다.");
        }
        
        try {
            Category.valueOf(form.getCategory());
        } catch (IllegalArgumentException e) {
            throw new ValidException("존재하지 않는 카테고리명 입니다.");
        }
    }
}
