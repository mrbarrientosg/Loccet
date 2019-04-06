package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.HomeController;
import cl.loccet.view.MenuBarView;

public class MenuBarRouter {

    public static MenuBarView create(HomeController controller) {
        MenuBarView view = Injectable.find(MenuBarView.class);

        view.setController(controller);

        return view;
    }
}
