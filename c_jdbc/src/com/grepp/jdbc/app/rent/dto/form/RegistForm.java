package com.grepp.jdbc.app.rent.dto.form;

import com.grepp.jdbc.app.rent.dto.RentDto;
import java.util.List;

public class RegistForm {
    
    private String userId;
    private List<Integer> bkIdxs;
    
    public RegistForm(String userId, List<Integer> bkIdxs) {
        this.userId = userId;
        this.bkIdxs = bkIdxs;
    }
    
    @Override
    public String toString() {
        return "RegistForm{" +
                   "userId='" + userId + '\'' +
                   ", bkIdxs=" + bkIdxs +
                   '}';
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public List<Integer> getBkIdxs() {
        return bkIdxs;
    }
    
    public void setBkIdxs(List<Integer> bkIdxs) {
        this.bkIdxs = bkIdxs;
    }

}
