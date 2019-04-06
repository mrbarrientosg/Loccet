package cl.loccet.view;

import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarView extends View {

    @FXML
    private MenuBar menuBar;

    @Override
    public void viewDidLoad() {
        System.out.println(menuBar.getMenus().get(0).getId());
    }

    @Override
    public void viewDidClose() {

    }
}
