package com.grepp.jdbc.app.rent.validator;

import com.grepp.jdbc.app.rent.dao.RentBookDao;
import com.grepp.jdbc.app.rent.dto.form.ReturnForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;

public class ExistsRbIdxValidator implements Validator<Integer> {

    private final RentBookDao rentBookDao = RentBookDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(Integer rbIdx) {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            if(rentBookDao.selectById(conn, rbIdx).isEmpty())
                throw new ValidException("존재하지 않는 대출도서 입니다.");
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
