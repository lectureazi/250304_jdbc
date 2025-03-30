package com.grepp.jdbc.app.member.dto.form;

import com.grepp.jdbc.app.member.code.Grade;
import com.grepp.jdbc.app.member.dto.MemberDto;

public class SignupForm {
    
    private String userId;
    private String password;
    private String email;
    private Grade grade;
    private String tell;
    
    public MemberDto toDto() {
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(userId);
        memberDto.setPassword(password);
        memberDto.setEmail(email);
        memberDto.setGrade(grade);
        memberDto.setTell(tell);
        return memberDto;
    }
    
    @Override
    public String toString() {
        return "SignupForm{" +
                   "userId='" + userId + '\'' +
                   ", password='" + password + '\'' +
                   ", email='" + email + '\'' +
                   ", grade=" + grade +
                   ", tell='" + tell + '\'' +
                   '}';
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Grade getGrade() {
        return grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
    public String getTell() {
        return tell;
    }
    
    public void setTell(String tell) {
        this.tell = tell;
    }
}
