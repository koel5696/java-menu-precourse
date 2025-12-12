package menu.domain.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomUtil {

    private RandomUtil() {
    }

    public static int generateCategoryNumber() {
        return Randoms.pickNumberInRange(1, 5);
    }

    public static String menuShuffle(List<String> menus) {
        return Randoms.shuffle(menus).getFirst();
    }
}
