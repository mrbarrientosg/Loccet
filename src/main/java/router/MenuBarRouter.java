package router;


import base.Injectable;
import controller.HomeController;
import view.MenuBarView;

public final class MenuBarRouter {

    public static MenuBarView create(HomeController controller) {
        MenuBarView view = Injectable.find(MenuBarView.class);

        view.setController(controller);

        return view;
    }
}
