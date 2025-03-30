package com.grepp.jdbc.app.book.validator;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;

public class BkIdxValidator implements Validator<Integer> {
    
    BookDao bookDao = BookDao.getInstance();
    JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(Integer bkIdx) {
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            if (bookDao.selectById(conn, bkIdx).isEmpty()) {
                throw new ValidException("존재하지 않는 도서 입니다.");
            }
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
}
