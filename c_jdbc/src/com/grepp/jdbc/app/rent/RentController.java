package com.grepp.jdbc.app.rent;

import com.google.gson.Gson;
import com.grepp.jdbc.app.member.validator.ExistsUserIdValidator;
import com.grepp.jdbc.app.rent.dto.form.RegistForm;
import com.grepp.jdbc.app.rent.validator.ExistsRmIdxValidator;
import com.grepp.jdbc.app.rent.validator.RegistValidator;
import com.grepp.jdbc.app.rent.validator.ExistsRbIdxValidator;
import com.grepp.jdbc.infra.json.GsonProvider;
import java.util.List;
import java.util.stream.Collectors;

// 데이터베이스에 auto increment key 로 증가된 값 받아오는 메서드
// https://docs.oracle.com/en/java/javase/21/docs/api/java.sql/java/sql/Connection.html#prepareStatement(java.lang.String,int)
// https://docs.oracle.com/en/java/javase/21/docs/api/java.sql/java/sql/Statement.html#getGeneratedKeys()
public class RentController {
    
    private final RegistValidator registValidator = new RegistValidator();
    private final ExistsRbIdxValidator existsRbIdxValidator = new ExistsRbIdxValidator();
    private final ExistsUserIdValidator existsUserIdValidator = new ExistsUserIdValidator();
    private final ExistsRmIdxValidator existsRmIdxValidator = new ExistsRmIdxValidator();
    private static final RentController instance = new RentController();
    private final Gson gson = GsonProvider.get();
    private final RentService rentService = RentService.getInstance();
    
    
    private RentController() {
    }
    
    public static RentController getInstance() {
        return instance;
    }
    
    public String registRent(String userId, List<String> bkIdxs) {
        List<Integer> parsed = bkIdxs.stream().map(Integer::parseInt).collect(Collectors.toList());
        RegistForm form = new RegistForm(userId, parsed);
        registValidator.validate(form);
        return gson.toJson(rentService.insertRent(userId, parsed));
    }
    
    public String returnRentBook(String rbIdx) {
        int parsed = Integer.parseInt(rbIdx);
        existsRbIdxValidator.validate(parsed);
        return gson.toJson(rentService.returnRent(parsed));
    }
    
    public String overdueRentBook(String rbIdx) {
        int parsed = Integer.parseInt(rbIdx);
        existsRbIdxValidator.validate(parsed);
        return gson.toJson(rentService.overdueRent(parsed));
    }
    
    public String getByUserId(String userId) {
        existsUserIdValidator.validate(userId);
        return gson.toJson(rentService.selectByUserId(userId));
    }
    
    public String getRentDetail(String rmIdx) {
        int parsed = Integer.parseInt(rmIdx);
        existsRmIdxValidator.validate(parsed);
        return gson.toJson(rentService.selectRentBooks(parsed));
    }
    
    
}
