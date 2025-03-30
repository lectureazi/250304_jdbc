package com.grepp.jdbc.view.member;

import com.grepp.jdbc.app.member.MemberController;
import com.grepp.jdbc.app.member.code.Grade;
import com.grepp.jdbc.app.member.dto.form.SignupForm;
import java.util.Scanner;

public class MemberMenu {
    
    private final MemberController memberController = MemberController.getInstance();
    
    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*** login ***");
        System.out.print(" * id : ");
        String userId = sc.next();
        
        System.out.print(" * password : ");
        String password = sc.next();
        
        memberController.login(userId, password);
    }
    
    public void menu() {
        
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("\n*** 회원 관리 ***");
            System.out.println(" * 1. 회원 등록");
            System.out.println(" * 2. 회원 조회");
            System.out.println(" * 3. 전체 회원 조회");
            System.out.println(" * 4. 관리자 등록");
            System.out.println(" * 5. 암호 수정");
            System.out.println(" * 6. 탈퇴");
            System.out.println(" * 7. 이전 페이지");
            System.out.print("\n 입력 : ");
            
            switch (sc.nextInt()) {
                case 1:
                    System.out.println(memberController.signup(signUpForm(Grade.ROLE_USER)));
                    break;
                
                case 2:
                    System.out.print(" * 아이디 : ");
                    System.out.println(memberController.get(sc.next()));
                    break;
                
                case 3:
                    System.out.println(memberController.getAll());
                    break;
                
                case 4:
                    System.out.println(memberController.signup(signUpForm(Grade.ROLE_ADMIN)));
                    break;
                
                case 5:
                    System.out.print(" * 아이디 : ");
                    String userId = sc.next();
                    
                    System.out.print(" * 변경할 비밀번호 : ");
                    String password = sc.next();
                    
                    System.out.println(memberController.modifyPassword(userId, password));
                    break;
                
                case 6:
                    System.out.print(" * 아이디 : ");
                    System.out.println(memberController.leave(sc.next()));
                
                case 7:
                    return;
                default:
                    System.out.println(" system : 잘못 입력하셨습니다. 다시 입력하세요.");
            }
            
        } while (true);
    }
    
    public SignupForm signUpForm(Grade role) {
        Scanner sc = new Scanner(System.in);
        SignupForm member = new SignupForm();
        
        System.out.print(" * id : ");
        member.setUserId(sc.nextLine());
        
        System.out.print(" * password : ");
        member.setPassword(sc.nextLine());
        
        System.out.print(" * email : ");
        member.setEmail(sc.nextLine());
        
        System.out.print(" * tell : ");
        member.setTell(sc.nextLine());
        
        member.setGrade(role);
        return member;
    }
    
}
