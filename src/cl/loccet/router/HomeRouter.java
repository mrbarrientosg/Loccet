package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.HomeController;
import cl.loccet.model.Constructora;
import cl.loccet.view.HomeView;
import javafx.scene.Parent;

public class HomeRouter {

    public static HomeView create(Constructora model) {
        HomeView view = Injectable.find(HomeView.class);
        HomeRouter router = new HomeRouter();
        HomeController controller = new HomeController(view, model, router);

        view.setController(controller);

        return view;
    }

}
