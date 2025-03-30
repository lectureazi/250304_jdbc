package com.grepp.jdbc.app.member.validator;

import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.rent.dao.RentBookDao;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;
import java.util.Optional;

public class ExistsUserIdValidator implements Validator<String> {
    
    private final MemberDao memberDao = MemberDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(String userId) {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            if(memberDao.selectById(conn, userId).isEmpty())
                throw new ValidException("존재하지 않는 사용자 입니다.");
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
