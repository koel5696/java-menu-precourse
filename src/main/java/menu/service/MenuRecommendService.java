package menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.domain.util.NumberGenerator;
import menu.domain.util.ShuffleStrategy;

public class MenuRecommendService {
    private final NumberGenerator numberGenerator;
    private final ShuffleStrategy shuffleStrategy;

    public MenuRecommendService(NumberGenerator numberGenerator, ShuffleStrategy shuffleStrategy) {
        this.numberGenerator = numberGenerator;
        this.shuffleStrategy = shuffleStrategy;
    }

    public List<Menu> menuRecommend(Coaches coaches) {
        Map<Menu, Integer> menuCount = new HashMap<>();
        List<Menu> categoryResult = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Menu menu = pickCategory(menuCount);
            categoryResult.add(menu);
            for (Coach coach : coaches.getCoaches()) {
                recommendMenuForCoaches(coach, menu);
            }
        }
        
        return categoryResult;
    }

    private Menu pickCategory(Map<Menu, Integer> menuCount) {
        while (true) {
            int categoryNumber = numberGenerator.generateNumber();
            Menu menu = Menu.findCategory(categoryNumber);

            // 값이 없으면 0으로 치고 가져옴. 있으면 원래대로 << 기존 코드 최적화
            if (menuCount.getOrDefault(menu, 0) < 2) {
                menuCount.put(menu, menuCount.getOrDefault(menu, 0) + 1);
                return menu;
            }
        }
    }

    private void recommendMenuForCoaches(Coach coach, Menu menu) {
        while (true) {
            String menuResult = shuffleStrategy.shuffle(menu.getMenus());
            if (coach.canEat(menuResult)) {
                coach.addMenuResult(menuResult);
                break;
            }
        }
    }
}
