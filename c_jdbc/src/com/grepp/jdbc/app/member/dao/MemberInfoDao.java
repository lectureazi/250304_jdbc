package com.grepp.jdbc.app.member.dao;

import com.grepp.jdbc.app.member.dto.MemberInfoDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MemberInfoDao {
    
    public void insert(Connection conn, MemberInfoDto info) {
        String sql = "insert into member_info("
                         + "user_id, reg_date, login_date, modify_date, leave_date, rentable_date) "
                         + "values(?,?,?,?,?,?)";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
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
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, LocalDateTime.now());
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateLeaveDate(Connection conn, String userId) {
        String sql = "update member_info set leave_date=? where user_id=?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, LocalDateTime.now());
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
