package com.grepp.jdbc.app.member.validator;

import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;

public class MemberValidator implements Validator<MemberDto> {
    
    @Override
    public void validate(MemberDto dto) {
        if(dto.getUserId() == null || dto.getUserId().isBlank()){
            throw new ValidException("아이디는 공백일 수 없습니다.");
        }
        
        if(dto.getPassword() == null || dto.getPassword().length() < 6){
            throw new ValidException("비밀번호는 6자리 이상입니다.");
        }
    }
}
