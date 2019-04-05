package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AgregarTrabajadorView extends View {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button exitButton;

    @Override
    public void viewDidLoad() {
        exitButton.setOnAction(event -> {
            close();
            Injectable.find(LoginView.class).openWindow().show();
        });
    }

    @Override
    public void viewDidClose() {

    }

}
