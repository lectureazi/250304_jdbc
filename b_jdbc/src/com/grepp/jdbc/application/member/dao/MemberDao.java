package com.grepp.jdbc.application.member.dao;

import com.grepp.jdbc.application.member.code.Grade;
import com.grepp.jdbc.application.member.dto.MemberDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// NOTE 02 DAO
// Data Access Object
// 영속성 계층 : DBMS와 상호작용하는 Layer
// presentation layer - domain layer - persistence layer(DAO)
// 필요한 데이터 또는 데이터의 변경사항을 DB에 반영
// DB로 부터 읽어온 데이터를 domain layer 에서 사용하기 적합한 형태로 parsing
public class MemberDao {

    public MemberDto insert(MemberDto dto){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "insert into member(user_id, password, email, grade, tell) "
                         + "values(?,?,?,?,?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
           
            stmt.setString(1, dto.getUserId());
            stmt.setString(2, dto.getPassword());
            stmt.setString(3, dto.getEmail());
            stmt.setString(4, dto.getGrade().name());
            stmt.setString(5, dto.getTell());
            
            System.out.println(stmt);
            
            int res = stmt.executeUpdate();
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
        String sql = "select * from member where user_id = ? and password = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            
            stmt.setString(1, id);
            stmt.setString(2, password);
            MemberDto dto = new MemberDto();
            
            try(ResultSet rset = stmt.executeQuery()) {
                while(rset.next()){
                    dto.setUserId(rset.getString("user_id"));
                    dto.setPassword(rset.getString("password"));
                    dto.setEmail(rset.getString("email"));
                    dto.setTell(rset.getString("tell"));
                    dto.setLeave(rset.getBoolean("is_leave"));
                    dto.setGrade(Grade.valueOf(rset.getString("grade")));
                }
                return dto;
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
    public MemberDto update(MemberDto dto){
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf8";
        String sql = "update member set password = ? where user_id = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, dto.getPassword());
            stmt.setString(2, dto.getUserId());
            System.out.println(stmt);
            int res = stmt.executeUpdate();
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
        String sql = "delete from member where user_id = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(
            Connection conn = DriverManager.getConnection(url, "bm", "123qwe!@#");
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, dto.getUserId());
            int res = stmt.executeUpdate();
            return res > 0 ? dto : new MemberDto();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return new MemberDto();
    }
    
}
