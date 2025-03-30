package com.grepp.jdbc.app.rent.dto.form;

public class ReturnForm {
    
    private Integer rbIdx;
    
    public ReturnForm(Integer rbIdx) {
        this.rbIdx = rbIdx;
    }
    
    public Integer getRbIdx() {
        return rbIdx;
    }
    public void setRbIdx(Integer rbIdx) {
        this.rbIdx = rbIdx;
    }
}
