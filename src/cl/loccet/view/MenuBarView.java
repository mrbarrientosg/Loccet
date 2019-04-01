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
    protected void init() {
        System.out.println(menuBar.getMenus().get(0).getId());
    }

    @Override
    public MenuBar getRoot() {
        if(root == null){
            root = loadFXML();
        }
        return (MenuBar)root;
    }
}
