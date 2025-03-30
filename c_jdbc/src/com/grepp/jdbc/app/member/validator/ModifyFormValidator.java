package com.grepp.jdbc.app.member.validator;

import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.dto.form.ModifyForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;
import java.util.Optional;

public class ModifyFormValidator implements Validator<ModifyForm> {
    
    private final MemberDao memberDao = MemberDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    @Override
    public void validate(ModifyForm form) {
        
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            Optional<MemberDto> member = memberDao.selectById(conn, form.getUserId());
            
            if (member.isEmpty()) {
                throw new ValidException("존재하지 않는 회원입니다.");
            }
            
            if (form.getUserId() == null || form.getUserId().isBlank()) {
                throw new ValidException("아이디는 공백일 수 없습니다.");
            }
            
            if (form.getPassword() == null || form.getPassword().length() < 6) {
                throw new ValidException("비밀번호는 6자리 이상입니다.");
            }
        }finally {
            jdbcTemplate.close(conn);
        }
        
    }
}
