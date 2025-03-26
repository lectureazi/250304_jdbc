package com.grepp.jdbc.application.member.dao;

import com.grepp.jdbc.application.member.code.Grade;
import com.grepp.jdbc.application.member.dto.MemberDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

// NOTE 02 DAO
// Data Access Object
// 영속성 계층 : DBMS와 상호작용하는 Layer
// presentation layer - domain layer - persistence layer(DAO)
// 필요한 데이터 또는 데이터의 변경사항을 DB에 반영
// DB로 부터 읽어온 데이터를 domain layer 에서 사용하기 적합한 형태로 parsing
public class MemberDao {

    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    public Optional<MemberDto> insert(MemberDto dto){
        
        String sql = "insert into member(user_id, password, email, grade, tell) "
                         + "values(?,?,?,?,?)";
        
        try(
            Connection conn = jdbcTemplate.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
           
            stmt.setString(1, dto.getUserId());
            stmt.setString(2, dto.getPassword());
            stmt.setString(3, dto.getEmail());
            stmt.setString(4, dto.getGrade().name());
            stmt.setString(5, dto.getTell());
            
            int res = stmt.executeUpdate();
            return res > 0 ? Optional.of(dto) : Optional.empty();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public Optional<MemberDto> selectByIdAndPassword(String id, String password){
        String sql = "select * from member where user_id = ? and password = ?";
        
        try(
            Connection conn = jdbcTemplate.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            
            stmt.setString(1, id);
            stmt.setString(2, password);
            MemberDto dto = null;
            
            try(ResultSet rset = stmt.executeQuery()) {
                while(rset.next()){
                    dto = new MemberDto();
                    dto.setUserId(rset.getString("user_id"));
                    dto.setPassword(rset.getString("password"));
                    dto.setEmail(rset.getString("email"));
                    dto.setTell(rset.getString("tell"));
                    dto.setLeave(rset.getBoolean("is_leave"));
                    dto.setGrade(Grade.valueOf(rset.getString("grade")));
                }
                
                return Optional.ofNullable(dto);
            }
            
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public Optional<MemberDto> update(MemberDto dto){
        String sql = "update member set password = ? where user_id = ?";
        
        try(
            Connection conn = jdbcTemplate.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, dto.getPassword());
            stmt.setString(2, dto.getUserId());
            System.out.println(stmt);
            int res = stmt.executeUpdate();
            return res > 0 ? Optional.of(dto) : Optional.empty();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public boolean delete(MemberDto dto){
        String sql = "delete from member where user_id = ?";
        
        try(
            Connection conn = jdbcTemplate.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, dto.getUserId());
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
}
