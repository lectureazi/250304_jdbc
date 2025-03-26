package com.grepp.jdbc.infra.db;

import com.grepp.jdbc.infra.exception.JdbcInitializeException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {

    private String url;
    private String user;
    private String password;
    
    public JdbcTemplate(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new JdbcInitializeException(e.getMessage(), e);
        }
    }
    
    public Connection getConnection()  {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
}
