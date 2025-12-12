package menu;


import menu.controller.MenuController;
import menu.domain.util.CategoryNumberGenerator;
import menu.domain.util.MenuShuffle;
import menu.service.MenuRecommendService;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MenuController menuController = new MenuController(
                new OutputView(),
                new InputView(),
                new MenuRecommendService(
                        new CategoryNumberGenerator(),
                        new MenuShuffle()
                )
        );
        menuController.run();
    }
}
