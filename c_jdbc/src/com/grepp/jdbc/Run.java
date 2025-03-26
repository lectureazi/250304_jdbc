package com.grepp.jdbc;

import com.grepp.jdbc.app.member.code.Grade;
import com.grepp.jdbc.app.member.dao.MemberDao;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.view.Index;
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
           
            new Index().menu();
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
}
