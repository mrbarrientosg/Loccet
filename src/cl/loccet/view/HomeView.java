package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.HomeController;
import cl.loccet.router.AgregarTrabajadorRouter;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private MenuBarView menuBarView = Injectable.find(MenuBarView.class);

    private HomeController controller;

    public void setController(HomeController controller) {
        this.controller = controller;
    }

    public HomeView() {
        super("General");
    }

    @Override
    public void viewDidLoad() {
        getRoot().setTop(menuBarView.getRoot());
    }

    @Override
    public void viewDidClose() {

    }

    @Override
    public BorderPane getRoot()  {
        return (BorderPane) root;
    }

    public void setCenter(Parent node) {
        getRoot().setCenter(node);
    }
}
