package com.grepp.jdbc;

import com.grepp.jdbc.application.member.code.Grade;
import com.grepp.jdbc.application.member.dao.MemberDao;
import com.grepp.jdbc.application.member.dto.MemberDto;

// NOTE 01 JDBC API
// JDBC : Java Database Connectivity
// API  : Application Programing Interface
public class Run {
    
    public static void main(String[] args) {
        MemberDao dao = new MemberDao();
        //insert(dao);
        //select(dao);
        //update(dao);
        delete(dao);
    }
    
    private static void delete(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("test");
        System.out.println(dao.delete(dto));
    }
    
    // NOTE 03 SQL injection
    // SQL 쿼리를 일반 입력 또는 양식 필드에 삽입하여 애플리케이션 코드의 취약성을 악용
    private static void update(MemberDao dao) {
        MemberDto dto = new MemberDto();
//        dto.setUserId("a' or 1=1 or user_id = 'a");
//        dto.setPassword("ㅋㅋㅋㅋㅋㅋㅋ");
        dto.setUserId("super");
        dto.setPassword("1111");
        System.out.println(dao.update(dto));
    }
    
    private static void select(MemberDao dao) {
        System.out.println(dao.selectByIdAndPassword("test", "9999"));
    }
    
    private static void insert(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("test");
        dto.setPassword("9999");
        dto.setEmail("test@gmail.com");
        dto.setTell("010-2222-3333");
        dto.setLeave(false);
        dto.setGrade(Grade.ROLE_ADMIN);
        System.out.println(dao.insert(dto));
    }
    
}
