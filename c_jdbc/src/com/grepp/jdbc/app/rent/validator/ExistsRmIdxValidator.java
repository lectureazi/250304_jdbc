package com.grepp.jdbc.app.rent.validator;

import com.grepp.jdbc.app.rent.dao.RentBookDao;
import com.grepp.jdbc.app.rent.dao.RentDao;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;

public class ExistsRmIdxValidator implements Validator<Integer> {

    private final RentDao rentDao = RentDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(Integer rmIdx) {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            if(rentDao.selectById(conn, rmIdx).isEmpty())
                throw new ValidException("존재하지 않는 대출건 입니다.");
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
