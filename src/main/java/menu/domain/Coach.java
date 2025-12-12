package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private static final String NAME_REGEX = "^[가-힣]{2,4}$";

    private final String name;
    private final List<String> uneatableMenu;
    private final List<String> menuResult;

    public Coach(String name) {
        validateName(name);
        this.name = name;
        this.uneatableMenu = new ArrayList<>();
        this.menuResult = new ArrayList<>();
    }

    private void validateName(String coach) {
        if (!coach.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 코치 이름은 한글 2~4자로 입력하세요.");
        }
    }

    public void addUneatableMenu(List<String> uneatableMenu) {
        ValidateUneatableMenuCount(uneatableMenu);
        uneatableMenu.forEach(menu -> {
            Menu.validateMenu(menu);
            this.uneatableMenu.add(menu);
        });

    }


    private void ValidateUneatableMenuCount(List<String> uneatableMenu) {
        if (uneatableMenu.size() > 2) {
            throw new IllegalArgumentException("[ERROR] 못 먹는 메뉴는 0~2개 입니다.");
        }
    }

    public boolean canEat(String menu) {
        return !uneatableMenu.contains(menu);
    }

    public void addMenuResult(String menu) {
        menuResult.add(menu);
    }

    public String getName() {
        return name;
    }

    public List<String> getMenuResult() {
        return menuResult;
    }
}
