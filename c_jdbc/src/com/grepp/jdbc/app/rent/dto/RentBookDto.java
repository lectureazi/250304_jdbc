package com.grepp.jdbc.app.rent.dto;

import com.grepp.jdbc.app.rent.code.RentState;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentBookDto {

    private Integer rbIdx;
    private Integer rmIdx;
    private Integer bkIdx;
    private LocalDateTime regDate;
    private RentState state;
    private LocalDate returnDate;
    
    @Override
    public String toString() {
        return "RentBookDto{" +
                   "rbIdx=" + rbIdx +
                   ", rmIdx=" + rmIdx +
                   ", bkIdx=" + bkIdx +
                   ", regDate=" + regDate +
                   ", status=" + state +
                   ", returnDate=" + returnDate +
                   '}';
    }
    
    public Integer getRbIdx() {
        return rbIdx;
    }
    
    public void setRbIdx(Integer rbIdx) {
        this.rbIdx = rbIdx;
    }
    
    public Integer getRmIdx() {
        return rmIdx;
    }
    
    public void setRmIdx(Integer rmIdx) {
        this.rmIdx = rmIdx;
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
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
