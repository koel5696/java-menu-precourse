package menu.controller;

import java.util.List;
import menu.domain.Coach;
import menu.domain.Coaches;
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
        Coaches coaches = inputCoach();
        inputUneatableMenu(coaches);
        List<Menu> result = menuService.menuRecommend(coaches);
        outputView.printRecommendResults(coaches, result);
    }

    public Coaches inputCoach() {
        while (true) {
            try {
                String inputCoaches = inputView.inputCoaches();
                return new Coaches(List.of(inputCoaches.split(",")));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public void inputUneatableMenu(Coaches coaches) {
        for (Coach coach : coaches.getCoaches()) {
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
