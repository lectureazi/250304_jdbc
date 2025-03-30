package com.grepp.jdbc.view.rent;

import com.grepp.jdbc.app.book.BookController;
import com.grepp.jdbc.app.rent.RentController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentMenu {
    
    private final BookController bookController = BookController.getInstance();
    private final RentController rentController = RentController.getInstance();
    Scanner sc = new Scanner(System.in);
    
    public void menu() {
        do {
                System.out.println("\n*** 대출 관리 ***");
                System.out.println(" * 1. 도서 대출");
                System.out.println(" * 2. 도서 반납");
                System.out.println(" * 3. 도서 연장");
                System.out.println(" * 4. 대출 중인 대출건 조회");
                System.out.println(" * 5. 끝내기");
                System.out.print("\n 입력 : ");
                
                switch (sc.nextInt()) {
                    
                    // todo :
                    // 1. 대출자의 아이디를 입력 받습니다.
                    // 3. 모든 도서 정보를 출력합니다.
                    // 4. 대출할 도서의 도서번호를 반복하여 입력받습니다.
                    // 5. 도서를 대출 합니다.
                    // 6. 대출자의 대출가능일자가 오늘 이후인 경우 대출을 취소합니다.
                    // 7. 대출히스토리 테이블에 히스토리를 추가합니다.
                    
                    // 대출자아이디, 대출건이름은 공백일 수 없습니다.
                    // 대출도서수량은 0보다 작을 수 없습니다.
                    // 대출도서(rent_book)의 상태에는 RENT, OVERDUE, RETURN이 있습니다.
                    case 1:
                        System.out.print(" * user_id : ");
                        String userId = sc.next();
                        
                        System.out.println(bookController.getAll());
                        List<String> bkIdxs = new ArrayList<>();
                        
                        while (true) {
                            System.out.print(" * 도서번호(bkIdx) : ");
                            bkIdxs.add(sc.next());
                            
                            System.out.print("\n system : 대출할 도서가 더 존재하나요?(y/n) : ");
                            if (sc.next().equalsIgnoreCase("N")) {
                                break;
                            }
                        }
                        
                        System.out.println(rentController.registRent(userId, bkIdxs));
                        break;
                        
                    // todo :
                    // 1. 사용자가 입력한 번호의 대출도서의 상태를 반납상태로 변경합니다.
                    // 2. 대출히스토리 테이블에 히스토리를 추가합니다.
                    // 3. 대출도서가 속한 대출건의 모든 대출도서가 반납처리 되었다면, 대출건의 상태를 반납완료상태로 변경합니다.
                    // 4. 대출자의 대출가능일자를 당일로 변경합니다.
                    // 5. 만약 반납일이 반납예정일 이후라면, (당일 + 연체일자 * 3일)로 대출가능일자를 변경합니다.
                    case 2:
                        System.out.print(" * 대출도서번호(rbIdx) : ");
                        String rbIdx = sc.next();
                        System.out.println(rentController.returnRentBook(rbIdx));
                        break;
                    
                    // todo :
                    // 1. 사용자가 입력한 번호의 대출도서의 상태를 연장상태로 변경합니다.
                    // 2. 대출도서에 반납일자를 해당 반납일자의 1주일 뒤로 변경합니다.
                    // 3. 대출히스토리 테이블에 히스토리를 추가합니다.
                    case 3:
                        System.out.print(" * 대출도서번호(rbIdx) : ");
                        System.out.println(rentController.overdueRentBook(sc.next()));
                        break;
                    
                    // todo :
                    // 1. 입력받은 사용자가 대출한 모든 대출 정보를 출력합니다.
                    // 2. 대출 번호를 입력하면 해당 대출건의 모든 대출도서 정보를 출력합니다.
                    case 4:
                        System.out.print(" * user_id : ");
                        System.out.println(rentController.getByUserId(sc.next()));
                        System.out.print(" * rm_idx : ");
                        System.out.println(rentController.getRentDetail(sc.next()));
                        break;
                    
                    case 5:
                        return;
                    
                    default:
                        System.out.println(" system : 잘못된 숫자를 입력하셨습니다.");
                }
        } while (true);
    }
    
}
