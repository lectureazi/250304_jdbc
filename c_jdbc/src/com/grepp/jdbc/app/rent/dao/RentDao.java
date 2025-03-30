package com.grepp.jdbc.app.rent.dao;

import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.rent.dto.RentDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentDao {
    
    private static final RentDao instance = new RentDao();
    
    private RentDao() {
    }
    
    public static RentDao getInstance() {
        return instance;
    }
    
    public int insertRent(Connection conn, RentDto rent) {
    
        String sql = "insert into rent_master(user_id, reg_date, is_return, title, rent_book_cnt)"
                         + " values(?,?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            ps.setString(1, rent.getUserId());
            ps.setObject(2, rent.getRegDate());
            ps.setBoolean(3, rent.getReturn());
            ps.setString(4, rent.getTitle());
            ps.setInt(5, rent.getRentBookCnt());
            ps.executeUpdate();
            try(ResultSet rset = ps.getGeneratedKeys()){
                if(rset.next()){
                   return rset.getInt(1);
                }
            }
            
            return -1;
        
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
    }
    
    public Optional<RentDto> selectById(Connection conn, Integer rmIdx) {
        String sql = "select * from rent_master where rm_idx = ?";
        RentDto res = null;
        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, rmIdx);
            try (ResultSet rset = stmt.executeQuery()) {
                
                while (rset.next()) {
                    res = generateRent(rset);
                }
                
                return Optional.ofNullable(res);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    private static RentDto generateRent(ResultSet rset) throws SQLException {
        RentDto res = new RentDto();
        res.setRmIdx(rset.getInt("rm_idx"));
        res.setUserId(rset.getString("user_id"));
        res.setRegDate(rset.getObject("reg_date", LocalDateTime.class));
        res.setReturn(rset.getBoolean("is_return"));
        res.setTitle(rset.getString("title"));
        res.setRentBookCnt(rset.getInt("rent_book_cnt"));
        return res;
    }
    
    public List<RentDto> selectByUserId(Connection conn, String userId) {
        String sql = "select * from rent_master where user_id = ?";
        
        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            List<RentDto> rents = new ArrayList<>();
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    rents.add(generateRent(rset));
                }
                
                return rents;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
}
