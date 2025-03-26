package com.grepp.jdbc.app.book;

import com.google.gson.Gson;
import com.grepp.jdbc.app.book.dto.form.RegistForm;
import com.grepp.jdbc.infra.json.GsonProvider;


public class BookController {
    
    private final BookService bookService = new BookService();
    private final Gson gson = GsonProvider.get();
    
    public String findAllBooks() {
        return gson.toJson(bookService.selectAllBook());
    }
    
    public String registBook(RegistForm form) {
        return null;
    }
    
    public String modifyBookInfo(String bkIdx, String info) {
        return null;
    }
    
    public String deleteBook(String bkIdx) {
        return null;
    }
}
