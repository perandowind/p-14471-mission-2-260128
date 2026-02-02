package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        int count = 0;
        List<Storage> storages = new ArrayList<>();

        while(true){
            System.out.print("명령) ");
            String command = sc.nextLine();
            Rq rq = new Rq(command); //명령 분석

            if(command.equals("종료")) return;
            else if (command.equals("등록")) {
                System.out.print("명언: ");
                String quote = sc.nextLine();
                System.out.print("작가: ");
                String author = sc.nextLine();
                count++;
                storages.add(new Storage(count, author, quote));
                System.out.println(count + "번 명언이 등록되었습니다.");
            }

            else if (command.equals("목록")) {
                printList(storages);
            }

            else if (rq.getAction().equals("삭제")) {
                // Map("id" : 1)의 형태로 저장됨
                int deleteNo = rq.getIntParam("id", -1);

                if(deleteNo == -1) {
                    System.out.println("삭제할 번호를 올바르게 입력해주세요. (예: 삭제?id=1)"); // 삭제?id=aa 처럼 입력한 경우
                    continue;
                }

                boolean isExist = false;
                for (Storage s : storages) {
                    if (s.quoteNo == deleteNo) {
                        storages.remove(s);
                        System.out.println(deleteNo + "번 명언이 삭제되었습니다.");
                        isExist = true;
                        break;
                    }
                }
                //리스트에 존재하지 않으면 출력
                if (!isExist) {
                    System.out.println(deleteNo + "번 명언은 존재하지 않습니다.");
                }
            }

            else if (rq.getAction().equals("수정")) {
                int modifyNo = rq.getIntParam("id", -1);

                if(modifyNo == -1) {
                    System.out.println("수정할 번호를 올바르게 입력해주세요. (예: 수정?id=1)");
                    continue;
                }
                // 해당 번호에 명언이 있는지 탐색
                Storage foundStorage = null;
                for (Storage s : storages) {
                    if (s.quoteNo == modifyNo) {
                        foundStorage = s;
                        break;
                    }
                }

                // 찾았으면 수정진행
                if (foundStorage == null) {
                    System.out.println(modifyNo + "번 명언은 존재하지 않습니다.");
                }else {
                    System.out.println("명언(기존) : " + foundStorage.quote);
                    System.out.print("명언 : ");
                    String modifiedQuote = sc.nextLine();

                    System.out.println("작가(기존) : " + foundStorage.author);
                    System.out.print("작가 : ");
                    String modifiedAuthor = sc.nextLine();

                    foundStorage.quote = modifiedQuote;
                    foundStorage.author = modifiedAuthor;
                }

            }
        }
    }

    private static void printList(List<Storage> storages) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = storages.size() - 1; i >=0 ; i--) {
            System.out.println(storages.get(i).quoteNo + " / " + storages.get(i).author + " / " + storages.get(i).quote);
        }
    }
}
