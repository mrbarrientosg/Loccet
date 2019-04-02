package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private MenuBarView menuBarView = Injectable.getIntance().find(MenuBarView.class);

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
            Injectable.getIntance().find(LoginView.class).openWindow();
        });


        getRoot().setTop(menuBarView.getRoot());
    }

    @Override
    public BorderPane getRoot()  {
        return (BorderPane) root;
    }
}
