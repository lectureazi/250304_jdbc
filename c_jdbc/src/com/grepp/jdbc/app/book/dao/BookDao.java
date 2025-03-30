package com.grepp.jdbc.app.book.dao;

import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {
    
    private static final BookDao instance = new BookDao();
    
    private BookDao() {
    }
    
    public static BookDao getInstance() {
        return instance;
    }
    
    public List<BookDto> selectAll(Connection conn) {
        List<BookDto> books = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("select * from book")) {
            try (ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    BookDto book = generateBookDto(rs);
                    books.add(book);
                }
                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public BookDto insertBook(Connection conn, BookDto dto) {
        String sql =
            "insert into book(isbn, category, title, author, info, book_amt, reg_date, rent_cnt)"
                + " values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getIsbn());
            ps.setString(2, dto.getCategory().name());
            ps.setString(3, dto.getTitle());
            ps.setString(4, dto.getAuthor());
            ps.setString(5, dto.getInfo());
            ps.setInt(6, dto.getAmount());
            ps.setObject(7, dto.getRegDate());
            ps.setInt(8, 0);
            
            ps.executeUpdate();
            return dto;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void deleteBook(Connection conn, int bkIdx) {
        String sql = "delete from book where bk_idx = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bkIdx);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void updateBookInfo(Connection conn, int bkIdx, String info) {
        String sql = "update book set info = ? where bk_idx = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, info);
            ps.setInt(2, bkIdx);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public Optional<BookDto> selectById(Connection conn, int bkIdx) {
        
        String sql = "select * from book where bk_idx = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bkIdx);
            
            try (ResultSet rset = ps.executeQuery()) {
                BookDto book = null;
                while (rset.next()) {
                    book = generateBookDto(rset);
                }
                
                return Optional.ofNullable(book);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public boolean existAllBooks(Connection conn, List<Integer> bkIdxs) {
        
        StringBuffer inStatement = new StringBuffer(bkIdxs.getFirst());
        inStatement.append("?");
        for (int i = 1; i < bkIdxs.size(); i++) {
            inStatement.append(",?");
        }
        
        String sql = "select count(*) from book where bk_idx in (" + inStatement + ")";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            for (int i = 1; i <= bkIdxs.size(); i++) {
                ps.setInt(i, bkIdxs.get(i - 1));
            }
            
            int res = 0;
            try (ResultSet rset = ps.executeQuery()) {
                while (rset.next()) {
                    res = rset.getInt(1);
                    System.out.println(res);
                }
                return res == bkIdxs.size();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    private BookDto generateBookDto(ResultSet rs) throws SQLException {
        BookDto book = new BookDto();
        book.setBkIdx(rs.getInt("bk_idx"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setRegDate(rs.getObject("reg_date", LocalDateTime.class));
        return book;
    }
    
    
}
