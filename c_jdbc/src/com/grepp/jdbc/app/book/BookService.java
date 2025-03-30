package com.grepp.jdbc.app.book;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.util.List;

public class BookService {
    
    private static final BookService instance = new BookService();
    private final BookDao bookDao = BookDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    private BookService() {
    }

    public static BookService getInstance() {
        return instance;
    }
    
    public List<BookDto> selectAllBook() {
        
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            return bookDao.selectAll(conn);
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public BookDto insertBook(BookDto dto) {
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            BookDto result = bookDao.insertBook(conn, dto);
            jdbcTemplate.commit(conn);
            return result;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public BookDto deleteBook(int bkIdx) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            bookDao.deleteBook(conn, bkIdx);
            BookDto result = new BookDto();
            result.setBkIdx(bkIdx);
            
            jdbcTemplate.commit(conn);
            return result;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public BookDto updateBookInfo(int bkIdx, String info) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            bookDao.updateBookInfo(conn, bkIdx, info);
            BookDto result = new BookDto();
            result.setBkIdx(bkIdx);
            result.setInfo(info);
            jdbcTemplate.commit(conn);
            return result;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
}
