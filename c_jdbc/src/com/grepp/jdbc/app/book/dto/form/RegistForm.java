package com.grepp.jdbc.app.book.dto.form;

public class RegistForm {
    private String isbn;
    private String category;
    private String title;
    private String author;
    private String info;
    private String amount;
    
    @Override
    public String toString() {
        return "RegistForm{" +
                   "isbn='" + isbn + '\'' +
                   ", category='" + category + '\'' +
                   ", title='" + title + '\'' +
                   ", author='" + author + '\'' +
                   ", info='" + info + '\'' +
                   ", amount='" + amount + '\'' +
                   '}';
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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
}
