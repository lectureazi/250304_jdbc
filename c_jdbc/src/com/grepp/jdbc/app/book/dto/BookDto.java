package com.grepp.jdbc.app.book.dto;

import com.grepp.jdbc.app.book.code.Category;
import java.time.LocalDateTime;

public class BookDto {
    
    private Integer bkIdx;
    private String isbn;
    private String title;
    private String author;
    private String info;
    private Integer amount;
    private Integer rentCnt;
    private Category category;
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
                   ", rentCnt=" + rentCnt +
                   ", category=" + category +
                   ", regDate=" + regDate +
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
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public Integer getRentCnt() {
        return rentCnt;
    }
    
    public void setRentCnt(Integer rentCnt) {
        this.rentCnt = rentCnt;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public LocalDateTime getRegDate() {
        return regDate;
    }
    
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
