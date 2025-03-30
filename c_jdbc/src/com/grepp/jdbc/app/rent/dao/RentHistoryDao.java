package com.grepp.jdbc.app.rent.dao;

import com.grepp.jdbc.app.rent.dto.RentBookDto;
import com.grepp.jdbc.app.rent.dto.RentHistoryDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentHistoryDao {
    
    private static final RentHistoryDao instance = new RentHistoryDao();
    
    private RentHistoryDao() {
    }
    
    public static RentHistoryDao getInstance() {
        return instance;
    }
    
    public void insertRentHistory(Connection conn, RentBookDto rentBook)  {
        
        String sql = "insert into rent_history(rm_idx, rb_idx, bk_idx, state, reg_date)"
                         + " values(?, ?, ?, ?, ?)";
        
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rentBook.getRmIdx());
            ps.setInt(2, rentBook.getRbIdx());
            ps.setObject(3, rentBook.getBkIdx());
            ps.setString(4, rentBook.getState().name());
            ps.setObject(5, rentBook.getRegDate());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }

}
