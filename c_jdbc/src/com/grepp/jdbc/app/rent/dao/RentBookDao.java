package com.grepp.jdbc.app.rent.dao;

import com.grepp.jdbc.app.rent.code.RentState;
import com.grepp.jdbc.app.rent.dto.RentBookDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentBookDao {
    
    private static final RentBookDao instance = new RentBookDao();
    
    private RentBookDao() {
    }
    
    public static RentBookDao getInstance() {
        return instance;
    }
    
    public int insertRentBook(Connection conn, RentBookDto rentBook) {
        
        String sql = "insert into rent_book(rm_idx, bk_idx, reg_date, state, return_date)"
                         + " values(?, ?, ?, ?, ?)";
        
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rentBook.getRmIdx());
            ps.setInt(2, rentBook.getBkIdx());
            ps.setObject(3, rentBook.getRegDate());
            ps.setString(4, rentBook.getState().name());
            ps.setObject(5, rentBook.getReturnDate());
            
            ps.executeUpdate();
            try(ResultSet rset = ps.getGeneratedKeys()){
                while (rset.next()) {
                    return rset.getInt(1);
                }
            }
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
        
        return -1;
    }
    
    public Optional<RentBookDto> selectById(Connection conn, Integer rbIdx) {
        String sql = "select * from rent_book where rb_idx = ?";
        RentBookDto res = null;
        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            
            stmt.setInt(1, rbIdx);
            
            try (ResultSet rset = stmt.executeQuery()) {
                
                while (rset.next()) {
                    res = generateRentBook(rset);
                }
                
                return Optional.ofNullable(res);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    
    public void updateRentState(Connection conn, RentBookDto rentBook) {
        String sql = "update rent_book set state = ? where rb_idx = ?";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, rentBook.getState().name());
            ps.setInt(2, rentBook.getRbIdx());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateReturnDate(Connection conn, RentBookDto rentBook) {
        String sql = "update rent_book set state = ?, return_date = ? where rb_idx = ?";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, rentBook.getState().name());
            ps.setObject(2, rentBook.getReturnDate());
            ps.setInt(3, rentBook.getRbIdx());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public List<RentBookDto> selectByRmIdx(Connection conn, Integer rmIdx) {
        String sql = "select * from rent_book where rm_idx = ?";
        
        List<RentBookDto> rentBooks = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, rmIdx);
            try (ResultSet rset = ps.executeQuery()) {
                while (rset.next()) {
                    rentBooks.add(generateRentBook(rset));
                }
            }
            
            return rentBooks;
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    private RentBookDto generateRentBook(ResultSet rset) throws SQLException {
        RentBookDto res = new RentBookDto();
        res.setRbIdx(rset.getInt("rb_idx"));
        res.setRmIdx(rset.getInt("rm_idx"));
        res.setBkIdx(rset.getInt("bk_idx"));
        res.setRegDate(rset.getObject("reg_date", LocalDateTime.class));
        res.setReturnDate(rset.getObject("return_date", LocalDate.class));
        res.setState(RentState.valueOf(rset.getString("state")));
        return res;
    }
}
