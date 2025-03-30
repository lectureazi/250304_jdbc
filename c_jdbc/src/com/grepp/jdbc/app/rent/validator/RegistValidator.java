package com.grepp.jdbc.app.rent.validator;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.rent.dto.form.RegistForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;

public class RegistValidator implements Validator<RegistForm> {

    private final BookDao bookDao = BookDao.getInstance();
    private final MemberDao memberDao = MemberDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(RegistForm form) {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            if(memberDao.selectById(conn, form.getUserId()).isEmpty())
                throw new ValidException("존재하지 않는 사용자 입니다.");
            
            if(!bookDao.existAllBooks(conn, form.getBkIdxs()))
                throw new ValidException("존재하지 않는 도서가 포함되어 있습니다.");
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
