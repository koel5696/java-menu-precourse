package menu.domain.util;

import camp.nextstep.edu.missionutils.Randoms;

public class CategoryNumberGenerator implements NumberGenerator {

    @Override
    public int generateNumber() {
        return Randoms.pickNumberInRange(1, 5);
    }
}
