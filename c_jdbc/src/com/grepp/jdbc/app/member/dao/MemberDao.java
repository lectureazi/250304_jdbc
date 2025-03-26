package com.grepp.jdbc.app.member.dao;

import com.grepp.jdbc.app.member.code.Grade;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.dto.form.SignupForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// NOTE 03 DAO
// Data Access Object
// 영속성 계층 : DBMS와 상호작용하는 Layer
// presentation layer - domain layer - persistence layer(DAO)
// 필요한 데이터 또는 데이터의 변경사항을 DB에 반영
// DB로 부터 읽어온 데이터를 domain layer 에서 사용하기 적합한 형태로 parsing
public class MemberDao {

    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    public Optional<MemberDto> insert(Connection conn, MemberDto dto){
        
        String sql = "insert into member(user_id, password, email, grade, tell) "
                         + "values(?,?,?,?,?)";
        
        try( PreparedStatement stmt = conn.prepareStatement(sql);) {
           
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
            MemberDto res = null;
            stmt.setString(1, id);
            stmt.setString(2, password);
            
            try(ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    res = generateMemberDto(rset);
                }
                
                return Optional.ofNullable(res);
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
    
    public boolean delete(Connection conn, String userId){
        String sql = "delete from member where user_id = ?";
        try(
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public Optional<MemberDto> selectById(Connection conn, String userId) {
        String sql = "select * from member where user_id = ?";
        MemberDto res = null;
        try(
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            try(ResultSet rset = stmt.executeQuery()){
                
                while(rset.next()) {
                    res = generateMemberDto(rset);
                }
                
                return Optional.ofNullable(res);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    private MemberDto generateMemberDto(ResultSet rset) throws SQLException {
        MemberDto res = new MemberDto();
        res.setUserId(rset.getString("user_id"));
        res.setPassword(rset.getString("password"));
        res.setEmail(rset.getString("email"));
        res.setTell(rset.getString("tell"));
        res.setLeave(rset.getBoolean("is_leave"));
        res.setGrade(Grade.valueOf(rset.getString("grade")));
        return res;
    }
    
    public List<MemberDto> selectAll(Connection conn) {
        String sql = "select * from member";
        List<MemberDto> members = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            try(ResultSet rset = stmt.executeQuery()) {
                while(rset.next()){
                    members.add(generateMemberDto(rset));
                }
                
                return members;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updatePassword(Connection conn, MemberDto dto) {
        String sql = "update member set password = ? where user_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, dto.getPassword());
            stmt.setString(2, dto.getUserId());
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    
}
