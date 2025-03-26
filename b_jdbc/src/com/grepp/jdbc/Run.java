package com.grepp.jdbc;

import com.grepp.jdbc.application.member.code.Grade;
import com.grepp.jdbc.application.member.dao.MemberDao;
import com.grepp.jdbc.application.member.dto.MemberDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.util.Optional;

// NOTE 01 JDBC API
// JDBC : Java Database Connectivity
// API  : Application Programing Interface
public class Run {
    
    public static void main(String[] args) {
        
        try{
            String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
            String user = "bm";
            String password = "123qwe!@#";
            
            JdbcTemplate.init(url, user, password);
            
            MemberDao dao = new MemberDao();
            //insert(dao);
            //select(dao);
            //update(dao);
            //delete(dao);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        
        System.out.println("끝!!");
    }
    
    private static void delete(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("test");
        System.out.println(dao.delete(dto));
    }
    
    private static void update(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("super");
        dto.setPassword("9999");
        
        Optional<MemberDto> res = dao.update(dto);
        res.ifPresent(System.out::println);
    }
    
    private static void select(MemberDao dao) {
        Optional<MemberDto> res = dao.selectByIdAndPassword("test", "9999");
        
        if(res.isPresent()) {
            System.out.println(res.get());
            return;
        }
        
        System.out.println("존재하지 않는 사용자 입니다.");
        
    }
    
    private static void insert(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("test");
        dto.setPassword("9999");
        dto.setEmail("test@gmail.com");
        dto.setTell("010-2222-3333");
        dto.setLeave(false);
        dto.setGrade(Grade.ROLE_ADMIN);
        
        Optional<MemberDto> res = dao.insert(dto);
        res.ifPresent(System.out::println);
    }
    
}
