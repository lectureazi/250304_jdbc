package com.grepp.jdbc.app.book;

import com.grepp.jdbc.app.book.dto.BookDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    
    public List<BookDto> selectAll(Connection conn) {
        List<BookDto> books = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from book")) {
            try(ResultSet rs = ps.executeQuery()) {
            
                while(rs.next()){
                    BookDto book = new BookDto();
                    book.setBkIdx(rs.getInt("bk_idx"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setRegDate(rs.getObject("reg_date", LocalDateTime.class));
                    books.add(book);
                }
                
                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}
