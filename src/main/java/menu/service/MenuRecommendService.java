package menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.CoachNames;
import menu.domain.Menu;
import menu.domain.util.RandomUtil;

public class MenuRecommendService {

    public List<Menu> menuRecommend(CoachNames coachNames) {
        Map<Menu, Integer> menuCount = new HashMap<>();
        List<Menu> categoryResult = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Menu menu = validateDuplicateCategory(menuCount);
            categoryResult.add(menu);
            for (Coach coach : coachNames.getCoachNames()) {
                validateMenu(coach, menu);
            }
        }
        return categoryResult;
    }

    private Menu validateDuplicateCategory(Map<Menu, Integer> menuCount) {
        while (true) {
            int categoryNumber = RandomUtil.generateCategoryNumber();
            Menu menu = Menu.findCategory(categoryNumber);
            if (!menuCount.containsKey(menu)) {
                menuCount.put(menu, 1);
                return menu;
            }

            if (menuCount.get(menu) < 2) {
                menuCount.put(menu, menuCount.getOrDefault(menu, 0) + 1);
                return menu;
            }
        }
    }

    private void validateMenu(Coach coach, Menu menu) {
        while (true) {
            String menuResult = menu.getMenu();
            if (coach.canEat(menuResult)) {
                coach.addMenuResult(menuResult);
                break;
            }
        }
    }
}
