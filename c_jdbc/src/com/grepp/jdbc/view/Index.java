package com.grepp.jdbc.view;

import com.grepp.jdbc.app.member.auth.Principal;
import com.grepp.jdbc.app.member.auth.SecurityContext;
import com.grepp.jdbc.app.member.code.Grade;
import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.view.member.MemberMenu;
import java.util.Scanner;

public class Index {
    
    private final Scanner sc = new Scanner(System.in);
    private final MemberMenu memberMenu = new MemberMenu();
    
    public void menu() {
        while (true) {
            
            memberMenu.login();
            Principal principal = SecurityContext.getInstance().getPrincipal();
            
            if(principal.grade().equals(Grade.ANONYMOUS)) {
                System.out.println(" system : 아이디나 비밀번호를 확인하세요.");
                continue;
            }
            
            System.out.println(" system : 로그인에 성공했습니다.");
            
            if(!principal.grade().equals(Grade.ROLE_ADMIN)) {
                System.out.println(" system : 관리자만 접근할 수 있는 페이지 입니다.");
                continue;
            }
            
            try {
                System.out.println("\n*** menu ***");
                System.out.println(" * 1. 회원관리");
                System.out.println(" * 2. 도서관리");
                System.out.println(" * 3. 대출관리");
                System.out.println(" * 4. 종료");
                System.out.print("\n 입력 : ");
                
                switch (sc.nextInt()) {
                    case 1:
                        memberMenu.menu();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println(" system : 잘못된 숫자를 입력하셨습니다.");
                }
                
            } catch (DataAccessException e) {
                e.printStackTrace();
            } catch(ValidException e){
                System.out.println(" system : " + e.getMessage());
            }
        }
    }
}
