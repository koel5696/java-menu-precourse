package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputCoachNames() {
        System.out.println();
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        return Console.readLine();
    }

    public String inputUneatableMenu(String name) {
        System.out.println();
        System.out.println(name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
        return Console.readLine();
    }
}
