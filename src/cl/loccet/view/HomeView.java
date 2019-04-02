package cl.loccet.view;

import cl.loccet.base.Router;
import cl.loccet.base.RouterView;
import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private MenuBarView menuBarView = Router.getIntance().find(MenuBarView.class);

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button backButton;

    public HomeView() {
        super("General");
    }

    @Override
    public void viewDidLoad() {
        backButton.setOnAction(event -> {
            close();
            Router.getIntance().find(LoginView.class).openWindow();
        });


        getRoot().setTop(menuBarView.getRoot());
    }

    @Override
    public BorderPane getRoot()  {
        return (BorderPane) root;
    }
}
