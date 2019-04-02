package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.HomeController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private MenuBarView menuBarView = Injectable.getIntance().find(MenuBarView.class);

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
        getRoot().setCenter(Injectable.getIntance().find(AgregarTrabajadorView.class).getRoot());
    }

    @Override
    public BorderPane getRoot()  {
        return (BorderPane) root;
    }
}
