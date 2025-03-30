package com.grepp.jdbc.app.member;

// NOTE 01 MVC
// MVC : Model(Service, Dao), Controller, View 로 소프트웨어 구조를 구성하는 패턴
// Controller (Presentation Layer)
// 클라이언트와 직접 상호작용하는 Layer
// 핵심로직인 Model이 외부에 종속되지 않도록 분리하기 위해 Client와 직접 상호작용하는 Presentation Layer

import com.google.gson.Gson;
import com.grepp.jdbc.app.member.dto.form.LeaveForm;
import com.grepp.jdbc.app.member.dto.form.ModifyForm;
import com.grepp.jdbc.app.member.dto.form.SignupForm;
import com.grepp.jdbc.app.member.validator.LeaveFormValidator;
import com.grepp.jdbc.app.member.validator.ModifyFormValidator;
import com.grepp.jdbc.app.member.validator.SignupFormValidator;
import com.grepp.jdbc.infra.json.GsonProvider;
import java.util.Map;

// 1. 사용자의 입력값을 어플리케이션 내에서 사용하기 적합한 형태로 파싱
// 2. 요청에 대해 인가 처리를 하는 외벽역할
// 3. Client 에게 비지니스로직의 결과물을 어떤 형태(text/html, json) 로 보여줄 것인지 선택
public class MemberController {
    
    private static final MemberController instance = new MemberController();
    private final SignupFormValidator signupValidator = new SignupFormValidator();
    private final ModifyFormValidator modifyValidator = new ModifyFormValidator();
    private final LeaveFormValidator leaveValidator = new LeaveFormValidator();
    private final MemberService memberService = MemberService.getInstance();
    private final Gson gson = GsonProvider.get();
    
    private MemberController() {
    }

    public static MemberController getInstance() {
        return instance;
    }
    
    public String signup(SignupForm form) {
        signupValidator.validate(form);
        return gson.toJson(memberService.signup(form.toDto()));
    }
    
    public String get(String userId) {
        return gson.toJson(memberService.selectById(userId));
    }
    
    public String getAll() {
        return gson.toJson(memberService.selectAll());
    }
    
    public String modifyPassword(String userId, String password) {
        ModifyForm form = new ModifyForm();
        form.setUserId(userId);
        form.setPassword(password);
        modifyValidator.validate(form);
        return gson.toJson(memberService.updatePassword(form.toDto()));
    }
    
    public String leave(String userId) {
        LeaveForm form = new LeaveForm();
        form.setUserId(userId);
        leaveValidator.validate(form);
        return gson.toJson(memberService.deleteById(userId));
    }
    
    public String login(String userId, String password) {
        memberService.authenticate(userId, password);
        return gson.toJson(Map.of("result", "success"));
    }
}
