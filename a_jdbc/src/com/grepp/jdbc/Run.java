package com.grepp.jdbc;

import com.grepp.jdbc.member.code.Grade;
import com.grepp.jdbc.member.dao.MemberDao;
import com.grepp.jdbc.member.dto.MemberDto;

// NOTE 01 JDBC API
// JDBC : Java Database Connectivity
// API  : Application Programing Interface
public class Run {
    
    public static void main(String[] args) {
        MemberDao dao = new MemberDao();
        //insert(dao);
        //select(dao);
        update(dao);
        //delete(dao);
    }
    
    private static void delete(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("test");
        System.out.println(dao.delete(dto));
    }
    
    // NOTE 03 SQL injection
    private static void update(MemberDao dao) {
        MemberDto dto = new MemberDto();
        //dto.setUserId("a' or 1=1 or user_id = 'a");
        //dto.setPassword("ㅋㅋㅋㅋㅋㅋㅋ");
        //dto.setPassword("1111");
        System.out.println(dao.update(dto));
    }
    
    private static void select(MemberDao dao) {
        System.out.println(dao.selectByIdAndPassword("super", "9999"));
    }
    
    private static void insert(MemberDao dao) {
        MemberDto dto = new MemberDto();
        dto.setUserId("hmd");
        dto.setPassword("9999");
        dto.setEmail("test@gmail.com");
        dto.setTell("010-2222-3333");
        dto.setLeave(false);
        dto.setGrade(Grade.ROLE_ADMIN);
        System.out.println(dao.insert(dto));
    }
    
}
