package com.grepp.jdbc.app.book;

import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import java.sql.Connection;
import java.util.List;

public class BookService {
    
    private final BookDao bookDao = new BookDao();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    public List<BookDto> selectAllBook() {
        
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            return bookDao.selectAll(conn);
        }finally {
            jdbcTemplate.close(conn);
        }
    }
}
