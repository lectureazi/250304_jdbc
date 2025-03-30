package com.grepp.jdbc.app.member;

import com.grepp.jdbc.app.member.auth.Principal;
import com.grepp.jdbc.app.member.auth.SecurityContext;
import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dao.MemberInfoDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.dto.MemberInfoDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Note 02 Service
// 비지니스로직을 구현
// DB의 transaction 관리 (commit/rollback)
public class MemberService {
    
    private static final MemberService instance = new MemberService();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private final MemberDao memberDao = MemberDao.getInstance();
    private final MemberInfoDao memberInfoDao = MemberInfoDao.getInstance();
    private MemberService() {
    }

    public static MemberService getInstance() {
        return instance;
    }
    
    public MemberDto signup(MemberDto dto) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            memberDao.insert(conn, dto);
            MemberInfoDto info = new MemberInfoDto();
            
            info.setUserId(dto.getUserId());
            memberInfoDao.insert(conn, info);
            
            jdbcTemplate.commit(conn);
            return dto;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public MemberDto selectById(String userId) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            return memberDao.selectById(conn, userId).orElse(null);
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    
    public List<MemberDto> selectAll() {
        Connection conn = jdbcTemplate.getConnection();
        try {
            return memberDao.selectAll(conn);
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public MemberDto updatePassword(MemberDto dto) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            memberDao.updatePassword(conn, dto);
            memberInfoDao.updateModifyDate(conn, dto.getUserId());
            jdbcTemplate.commit(conn);
            return dto;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public MemberDto deleteById(String userId) {
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            memberDao.delete(conn, userId);
            memberInfoDao.updateLeaveDate(conn, userId);
            jdbcTemplate.commit(conn);
            MemberDto dto = new MemberDto();
            dto.setUserId(userId);
            dto.setLeave(true);
            return dto;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public void authenticate(String userId, String password) {
        Connection conn = jdbcTemplate.getConnection();
        
        SecurityContext securityContext = SecurityContext.getInstance();
        
        try {
            Optional<MemberDto> member = memberDao.selectByIdAndPassword(conn, userId, password);
            
            if (member.isPresent()) {
                MemberDto dto = member.get();
                Principal principal = new Principal(dto.getUserId(), dto.getGrade(),
                    LocalDateTime.now());
                securityContext.setPrincipal(principal);
                return;
            }
            securityContext.setPrincipal(Principal.ANONYMOUS);
        } finally {
            jdbcTemplate.close(conn);
        }
    }
}
