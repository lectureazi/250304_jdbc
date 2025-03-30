package com.grepp.jdbc.app.rent;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.app.member.dao.MemberInfoDao;
import com.grepp.jdbc.app.member.dto.MemberInfoDto;
import com.grepp.jdbc.app.rent.code.RentState;
import com.grepp.jdbc.app.rent.dao.RentBookDao;
import com.grepp.jdbc.app.rent.dao.RentDao;
import com.grepp.jdbc.app.rent.dao.RentHistoryDao;
import com.grepp.jdbc.app.rent.dto.RentBookDto;
import com.grepp.jdbc.app.rent.dto.RentDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.infra.exception.ValidException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentService {
    
    private final BookDao bookDao = BookDao.getInstance();
    private final RentDao rentDao = RentDao.getInstance();
    private final MemberInfoDao memberInfoDao = MemberInfoDao.getInstance();
    private final RentBookDao rentBookDao = RentBookDao.getInstance();
    private final RentHistoryDao rentHistoryDao = RentHistoryDao.getInstance();
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    private static final RentService instance = new RentService();
    
    private RentService() {
    }
    
    public static RentService getInstance() {
        return instance;
    }
    
    public RentDto insertRent(String userId, List<Integer> bkIdxs) {
        
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            
            MemberInfoDto memberInfo = memberInfoDao.selectById(conn, userId).get();
            LocalDateTime now = LocalDateTime.now();
            long diff = memberInfo.getRentableDate().until(now, ChronoUnit.DAYS);
            
            if (diff < 0) {
                throw new ValidException("대출 권한이 없는 사용자 입니다.");
            }
            
            BookDto book = bookDao.selectById(conn, bkIdxs.getFirst()).get();
            String title =
                bkIdxs.size() > 1 ? book.getTitle() + " 외 " + (bkIdxs.size() -1 )+ "권" : book.getTitle();
            
            RentDto rent = generateRentDto(userId, bkIdxs, title);
            int rmIdx = rentDao.insertRent(conn, rent);
            
            for (int bkIdx : bkIdxs) {
                RentBookDto rentBook = generateRentBookDto(bkIdx, rmIdx);
                int rbIdx = rentBookDao.insertRentBook(conn, rentBook);
                rentBook.setRbIdx(rbIdx);
                rentHistoryDao.insertRentHistory(conn, rentBook);
            }
            
            jdbcTemplate.commit(conn);
            return rent;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public RentBookDto returnRent(int rbIdx) {
        Connection conn = jdbcTemplate.getConnection();
        
        try {
            RentBookDto rentBook = rentBookDao.selectById(conn, rbIdx).orElseThrow();
            rentBook.setState(RentState.RETURN);
            rentBookDao.updateRentState(conn, rentBook);
            rentHistoryDao.insertRentHistory(conn, rentBook);
            
            RentDto rent = rentDao.selectById(conn, rentBook.getRmIdx()).get();
            
            LocalDate now = LocalDate.now();
            long diff = now.until(rentBook.getReturnDate(), ChronoUnit.DAYS);
            
            if (diff > 0) {
                memberInfoDao.updateRentableDate(conn, rent.getUserId(), now.plusDays(diff * 3));
            }
            
            List<RentBookDto> rentBooks = rentBookDao.selectByRmIdx(conn, rent.getRmIdx());
            if(rentBooks.stream().allMatch(e -> e.getState() == RentState.RETURN)) {
                rent.setReturn(true);
            }
            
            jdbcTemplate.commit(conn);
            return rentBook;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public RentBookDto overdueRent(int rbIdx) {
        Connection conn = jdbcTemplate.getConnection();
        try {
            RentBookDto rentBook = rentBookDao.selectById(conn, rbIdx).orElseThrow();
            rentBook.setState(RentState.OVERDUE);
            rentBook.setReturnDate(rentBook.getReturnDate().plusDays(7));
            rentBookDao.updateReturnDate(conn, rentBook);
            rentHistoryDao.insertRentHistory(conn, rentBook);
            jdbcTemplate.commit(conn);
            return rentBook;
        } catch (DataAccessException e) {
            jdbcTemplate.rollback(conn);
            throw e;
        } finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public List<RentDto> selectByUserId(String userId) {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            return rentDao.selectByUserId(conn, userId);
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    public List<RentBookDto> selectRentBooks(int rmIdx) {
        Connection conn = jdbcTemplate.getConnection();
        try{
            return rentBookDao.selectByRmIdx(conn, rmIdx);
        }finally {
            jdbcTemplate.close(conn);
        }
    }
    
    private RentDto generateRentDto(String userId, List<Integer> bkIdxs, String title) {
        RentDto rent = new RentDto();
        rent.setTitle(title);
        rent.setUserId(userId);
        rent.setReturn(false);
        rent.setRentBookCnt(bkIdxs.size());
        rent.setRegDate(LocalDateTime.now());
        return rent;
    }
    
    private RentBookDto generateRentBookDto(int bkIdx, int rmIdx) {
        RentBookDto rentBook = new RentBookDto();
        rentBook.setRmIdx(rmIdx);
        rentBook.setBkIdx(bkIdx);
        rentBook.setRegDate(LocalDateTime.now());
        rentBook.setState(RentState.RENT);
        rentBook.setReturnDate(LocalDate.now().plusDays(7));
        return rentBook;
    }
    
    
}
