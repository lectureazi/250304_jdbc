package com.grepp.jdbc.app.book.dto;

import java.time.LocalDateTime;

public class BookDto {

    private Integer bkIdx;
    private String isbn;
    private String title;
    private String author;
    private String info;
    private String amount;
    private Integer rentCnt;
    private LocalDateTime regDate;
    @Override
    public String toString() {
        return "BookDto{" +
                   "bkIdx=" + bkIdx +
                   ", isbn='" + isbn + '\'' +
                   ", title='" + title + '\'' +
                   ", author='" + author + '\'' +
                   ", info='" + info + '\'' +
                   ", amount='" + amount + '\'' +
                   ", regDate=" + regDate +
                   ", rentCnt=" + rentCnt +
                   '}';
    }
    
    public Integer getBkIdx() {
        return bkIdx;
    }
    
    public void setBkIdx(Integer bkIdx) {
        this.bkIdx = bkIdx;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getRegDate() {
        return regDate;
    }
    
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
    
    public Integer getRentCnt() {
        return rentCnt;
    }
    
    public void setRentCnt(Integer rentCnt) {
        this.rentCnt = rentCnt;
    }
}
