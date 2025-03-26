package com.grepp.jdbc.app.member;

import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.util.Optional;

// Note 02 Service
// 비지니스로직을 구현
// DB의 transaction 관리 (commit/rollback)
public class MemberService {
    
    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private MemberDao memberDao = new MemberDao();
    
    public Optional<MemberDto> signup(MemberDto dto) {
    
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            Optional<MemberDao> member = memberDao.selectById(conn, dto.getUserId());
            Optional<MemberDto> res = memberDao.insert(conn, dto);
            jdbcTemplate.commit(conn);
            return res;
        }catch (DataAccessException e){
            jdbcTemplate.rollback(conn);
            throw e;
        }finally {
            jdbcTemplate.close(conn);
        }
    }
}
