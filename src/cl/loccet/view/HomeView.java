package cl.loccet.view;

import cl.loccet.base.Router;
import cl.loccet.base.RouterView;
import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private MenuBarView menuBarView = new MenuBarView();

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button backButton;

    @Override
    public void viewDidLoad() {
        backButton.setOnAction(event -> {
            Router.getIntance().getView(RouterView.LOGIN).openModal();
            close();
        });


        getRoot().setTop(menuBarView.getRoot());
    }

    @Override
    public BorderPane getRoot()  {
        if (root == null)
            root = loadFXML();

        return (BorderPane) root;
    }
}
