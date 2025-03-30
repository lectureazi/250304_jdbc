package com.grepp.jdbc.view.book;

import com.grepp.jdbc.app.book.BookController;
import com.grepp.jdbc.app.book.dto.form.RegistForm;
import java.util.Scanner;

public class BookMenu {
    
    private final BookController bookController = BookController.getInstance();
    
    public void menu() {
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("\n*** 도서 관리 ***");
            System.out.println(" * 1. 전체 도서");
            System.out.println(" * 2. 도서 등록");
            System.out.println(" * 3. 도서 소개 수정");
            System.out.println(" * 4. 도서 삭제");
            System.out.println(" * 5. 이전 페이지");
            System.out.print("\n 입력 : ");
            
            switch (sc.nextInt()) {
                // todo : 모든 도서 정보를 출력합니다.
                case 1:
                    System.out.println(bookController.getAll());
                    break;
                // todo : 도서 등록 후 등록한 도서의 정보를 출력합니다.
                // 도서명, isbn, 작가, 카테고리는 공백일 수 없습니다.
                // 도서재고는 0보다 작을 수 없습니다.
                // 카테고리는 NOVEL, POEM, HUMANITY 가 존재합니다.
                case 2:
                    System.out.println(bookController.registBook(bookForm()));
                    break;
                // todo : 도서 소개를 수정한 후 수정된 도서의 정보를 출력합니다.
                case 3:
                    System.out.print(" * 도서번호(bkIdx) : ");
                    String bkIdx = sc.next();
                    System.out.print(" * 도서 소개 : ");
                    sc.nextLine();
                    String info = sc.nextLine();
                    System.out.println(bookController.modifyBookInfo(bkIdx, info));
                    break;
                // todo : 도서 삭제 후 삭제 성공여부를 출력합니다.
                case 4:
                    System.out.print(" * 도서번호(bkIdx) : ");
                    System.out.println(bookController.deleteBook(sc.next()));
                    break;
                
                case 5:
                    return;
                
                default:
                    System.out.println("system : 잘못된 번호를 입력하셨습니다.");
            }
            
        } while (true);
    }
    
    private RegistForm bookForm() {
        Scanner sc = new Scanner(System.in);
        RegistForm form = new RegistForm();
        System.out.print("isbn : ");
        form.setIsbn(sc.next());
        System.out.print("category : ");
        form.setCategory(sc.next());
        System.out.print("title : ");
        form.setTitle(sc.next());
        System.out.print("author : ");
        form.setAuthor(sc.next());
        System.out.print("info : ");
        form.setInfo(sc.next());
        System.out.print("amount : ");
        form.setAmount(sc.next());
        return form;
    }
}
