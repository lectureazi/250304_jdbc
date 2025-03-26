package com.grepp.jdbc.app.member.dto.form;

import com.grepp.jdbc.app.member.dto.MemberDto;

public class ModifyForm {
    
    private String userId;
    private String password;
    
    @Override
    public String toString() {
        return "ModifyForm{" +
                   "userId='" + userId + '\'' +
                   ", password='" + password + '\'' +
                   '}';
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public MemberDto toDto() {
        MemberDto dto = new MemberDto();
        dto.setUserId(userId);
        dto.setPassword(password);
        return dto;
    }
}
