package com.grepp.jdbc.app.member.dto;

import java.time.LocalDateTime;

public class MemberInfoDto {
    
    private String userId;
    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime loginDate;
    private LocalDateTime modifyDate;
    private LocalDateTime leaveDate;
    private LocalDateTime RentableDate = LocalDateTime.now();
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public LocalDateTime getRegDate() {
        return regDate;
    }
    
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
    
    public LocalDateTime getLoginDate() {
        return loginDate;
    }
    
    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }
    
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }
    
    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public LocalDateTime getLeaveDate() {
        return leaveDate;
    }
    
    public void setLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }
    
    public LocalDateTime getRentableDate() {
        return RentableDate;
    }
    
    public void setRentableDate(LocalDateTime rentableDate) {
        RentableDate = rentableDate;
    }
}
