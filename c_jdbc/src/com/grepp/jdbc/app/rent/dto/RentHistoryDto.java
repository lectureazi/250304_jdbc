package com.grepp.jdbc.app.rent.dto;

import com.grepp.jdbc.app.rent.code.RentState;
import java.time.LocalDateTime;

public class RentHistoryDto {
    
    private Integer rhIdx;
    private Integer rmIdx;
    private Integer rbIdx;
    private Integer bkIdx;
    private LocalDateTime regDate;
    private RentState state;
    
    @Override
    public String toString() {
        return "RentHistoryDto{" +
                   "rhIdx=" + rhIdx +
                   ", rmIdx=" + rmIdx +
                   ", rbIdx=" + rbIdx +
                   ", bkIdx=" + bkIdx +
                   ", regDate=" + regDate +
                   ", state=" + state +
                   '}';
    }
    
    public Integer getRhIdx() {
        return rhIdx;
    }
    
    public void setRhIdx(Integer rhIdx) {
        this.rhIdx = rhIdx;
    }
    
    public Integer getRmIdx() {
        return rmIdx;
    }
    
    public void setRmIdx(Integer rmIdx) {
        this.rmIdx = rmIdx;
    }
    
    public Integer getRbIdx() {
        return rbIdx;
    }
    
    public void setRbIdx(Integer rbIdx) {
        this.rbIdx = rbIdx;
    }
    
    public Integer getBkIdx() {
        return bkIdx;
    }
    
    public void setBkIdx(Integer bkIdx) {
        this.bkIdx = bkIdx;
    }
    
    public LocalDateTime getRegDate() {
        return regDate;
    }
    
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
    
    public RentState getState() {
        return state;
    }
    
    public void setState(RentState state) {
        this.state = state;
    }
}
