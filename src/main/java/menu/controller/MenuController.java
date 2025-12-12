package menu.controller;

import java.util.List;
import menu.domain.Coach;
import menu.domain.CoachNames;
import menu.domain.Menu;
import menu.service.MenuRecommendService;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MenuRecommendService menuService;

    public MenuController(OutputView outputView, InputView inputView, MenuRecommendService menuService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.menuService = menuService;
    }

    public void run() {
        outputView.printStartingMessage();
        CoachNames coachNames = inputCoach();
        inputUneatableMenu(coachNames);
        List<Menu> result = menuService.menuRecommend(coachNames);
        outputView.printRecommendResults(coachNames, result);
    }

    public CoachNames inputCoach() {
        while (true) {
            try {
                String inputCoachNames = inputView.inputCoachNames();
                return new CoachNames(List.of(inputCoachNames.split(",")));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public void inputUneatableMenu(CoachNames coachNames) {
        for (Coach coach : coachNames.getCoachNames()) {
            inputUneatableMenuFor(coach);
        }
    }

    private void inputUneatableMenuFor(Coach coach) {
        while (true) {
            try {
                String[] menus = inputView.inputUneatableMenu(coach.getName()).split(",");
                coach.addUneatableMenu(List.of(menus));
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }


}
