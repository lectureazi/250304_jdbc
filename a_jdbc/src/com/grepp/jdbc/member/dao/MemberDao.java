package com.grepp.jdbc.member.dao;

import com.grepp.jdbc.member.code.Grade;
import com.grepp.jdbc.member.dto.MemberDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// NOTE 02 DAO
// Data Access Object
// 영속성 계층 : DBMS와 상호작용하는 Layer
// presentation layer - domain layer - persistence layer(DAO)
// 필요한 데이터 또는 데이터의 변경사항을 DB에 반영
// DB로 부터 읽어온 데이터를 domain layer 에서 사용하기 적합한 형태로 parsing
public class MemberDao {

    public MemberDto insert(MemberDto dto){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "insert into member("
                         + "user_id, password, email,"
                         + "grade, tell, is_leave)"
                         + " values("
                         + "'"+ dto.getUserId() + "',"
                         + "'"+ dto.getPassword() + "',"
                         + "'"+ dto.getEmail() + "',"
                         + "'"+ dto.getGrade().name() + "',"
                         + "'"+ dto.getTell() + "',"
                         + dto.getLeave() + ")";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            Statement stmt = conn.createStatement();
        ) {
            int res = stmt.executeUpdate(sql);
            return res > 0 ? dto : new MemberDto();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
    public MemberDto selectByIdAndPassword(String id, String password){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "select * from member where user_id = '"
                         + id + "' and password = '" + password + "'";
        
        System.out.println(sql);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
        ) {
        
            MemberDto dto = new MemberDto();
            while(rset.next()){
                dto.setUserId(rset.getString("user_id"));
                dto.setPassword(rset.getString("password"));
                dto.setEmail(rset.getString("email"));
                dto.setTell(rset.getString("tell"));
                dto.setLeave(rset.getBoolean("is_leave"));
                dto.setGrade(Grade.valueOf(rset.getString("grade")));
            }
            
            return dto;
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
    public MemberDto update(MemberDto dto){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "update member set password = '"
                         + dto.getPassword() + "' where user_id = '" + dto.getUserId() + "'";
        
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            Statement stmt = conn.createStatement();
        ) {
            int res = stmt.executeUpdate(sql);
            return res > 0 ? dto : new MemberDto();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
    public MemberDto delete(MemberDto dto){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "delete from member where user_id = '" + dto.getUserId() + "'";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            Statement stmt = conn.createStatement();
        ) {
            int res = stmt.executeUpdate(sql);
            return res > 0 ? dto : new MemberDto();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
}
