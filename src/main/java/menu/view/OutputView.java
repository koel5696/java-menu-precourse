package menu.view;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Menu;

public class OutputView {

    public void printStartingMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRecommendResults(Coaches coaches, List<Menu> result) {
        System.out.println();
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        System.out.println(printCategory(result));
        for (Coach coach : coaches.getCoaches()) {
            System.out.print("[ " + coach.getName() + " | ");
            System.out.println(String.join(" | ", coach.getMenuResult()) + " ]");
        }
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    private String printCategory(List<Menu> result) {
        List<String> category = new ArrayList<>();
        for (Menu menu : result) {
            category.add(menu.getCategory());
        }
        return "[ 카테고리 | " + String.join(" | ", category) + " ]";
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
