package com.grepp.jdbc.app.rent.dto;

import java.time.LocalDateTime;

public class RentDto {
    private Integer rmIdx;
    private String userId;
    private LocalDateTime regDate;
    private Boolean isReturn;
    private String title;
    private Integer rentBookCnt;
    
    @Override
    public String toString() {
        return "RentDto{" +
                   "rmIdx=" + rmIdx +
                   ", userId='" + userId + '\'' +
                   ", regDate=" + regDate +
                   ", isReturn=" + isReturn +
                   ", title='" + title + '\'' +
                   ", rentBookCnt=" + rentBookCnt +
                   '}';
    }
    
    public Integer getRmIdx() {
        return rmIdx;
    }
    
    public void setRmIdx(Integer rmIdx) {
        this.rmIdx = rmIdx;
    }
    
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
    
    public Boolean getReturn() {
        return isReturn;
    }
    
    public void setReturn(Boolean aReturn) {
        isReturn = aReturn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getRentBookCnt() {
        return rentBookCnt;
    }
    
    public void setRentBookCnt(Integer rentBookCnt) {
        this.rentBookCnt = rentBookCnt;
    }
}
