package com.grepp.jdbc.app.book;

import com.google.gson.Gson;
import com.grepp.jdbc.app.book.dto.form.RegistForm;
import com.grepp.jdbc.app.book.validator.BkIdxValidator;
import com.grepp.jdbc.app.book.validator.RegistValidator;
import com.grepp.jdbc.infra.json.GsonProvider;

public class BookController {
    
    private static final BookController instance = new BookController();
    private final BookService bookService = BookService.getInstance();
    private final RegistValidator registValidator = new RegistValidator();
    private final BkIdxValidator bkIdxValidator = new BkIdxValidator();
    private final Gson gson = GsonProvider.get();
    private BookController() {
    }

    public static BookController getInstance() {
        return instance;
    }
    
    public String getAll() {
        return gson.toJson(bookService.selectAllBook());
    }
    
    public String registBook(RegistForm form) {
        registValidator.validate(form);
        return gson.toJson(bookService.insertBook(form.toDto()));
    }
    
    public String modifyBookInfo(String bkIdx, String info) {
        int parsed = Integer.parseInt(bkIdx);
        bkIdxValidator.validate(parsed);
        return gson.toJson(bookService.updateBookInfo(parsed, info));
    }
    
    public String deleteBook(String bkIdx) {
        int parsed = Integer.parseInt(bkIdx);
        bkIdxValidator.validate(parsed);
        return gson.toJson(bookService.deleteBook(parsed));
    }
}
