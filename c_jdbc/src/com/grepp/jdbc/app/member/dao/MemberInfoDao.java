package com.grepp.jdbc.app.member.dao;

import com.grepp.jdbc.app.member.dto.MemberInfoDto;
import com.grepp.jdbc.app.rent.dto.RentDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class MemberInfoDao {
    
    private static final MemberInfoDao instance = new MemberInfoDao();
    
    private MemberInfoDao() {
    }
    
    public static MemberInfoDao getInstance() {
        return instance;
    }
    
    public Optional<MemberInfoDto> selectById(Connection conn, String userId) {
        String sql = "select * from member_info where user_id = ?";
        MemberInfoDto res = null;
        
        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            try (ResultSet rset = stmt.executeQuery()) {
                
                while (rset.next()) {
                    res = new MemberInfoDto();
                    res.setUserId(rset.getString("user_id"));
                    res.setRegDate(rset.getObject("reg_date", LocalDateTime.class));
                    res.setLeaveDate(rset.getObject("leave_date", LocalDateTime.class));
                    res.setLoginDate(rset.getObject("login_date", LocalDateTime.class));
                    res.setModifyDate(rset.getObject("modify_date", LocalDateTime.class));
                    res.setRentableDate(rset.getObject("rentable_date", LocalDateTime.class));
                }
                
                return Optional.ofNullable(res);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public void insert(Connection conn, MemberInfoDto info) {
        String sql = "insert into member_info("
                         + "user_id, reg_date, login_date, modify_date, leave_date, rentable_date) "
                         + "values(?,?,?,?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, info.getUserId());
            ps.setObject(2, info.getRegDate());
            ps.setObject(3, info.getLoginDate());
            ps.setObject(4, info.getModifyDate());
            ps.setObject(5, info.getLeaveDate());
            ps.setObject(6, info.getRentableDate());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateModifyDate(Connection conn, String userId) {
        
        String sql = "update member_info set modify_date=? where user_id=?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, LocalDateTime.now());
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateLeaveDate(Connection conn, String userId) {
        String sql = "update member_info set leave_date=? where user_id=?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, LocalDateTime.now());
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateRentableDate(Connection conn, String userId, LocalDate localDate) {
        String sql = "update member_info set rentable_date=? where user_id=?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, localDate);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
