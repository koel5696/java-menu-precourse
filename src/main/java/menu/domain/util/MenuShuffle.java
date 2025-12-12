package menu.domain.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class MenuShuffle implements ShuffleStrategy {

    @Override
    public String shuffle(List<String> menus) {
        return Randoms.shuffle(menus).getFirst();
    }
}
