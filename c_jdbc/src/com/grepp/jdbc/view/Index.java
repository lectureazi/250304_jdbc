package com.grepp.jdbc.view;

import com.grepp.jdbc.infra.exception.DataAccessException;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.view.member.MemberMenu;
import java.util.Scanner;

public class Index {
    
    private final Scanner sc = new Scanner(System.in);
    private final MemberMenu memberMenu = new MemberMenu();
    
    public void menu() {
        while (true) {
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
