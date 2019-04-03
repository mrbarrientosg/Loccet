package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AgregarTrabajadorView extends View {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button exitButton;

    public AgregarTrabajadorView() {
        super("");
    }

    @Override
    public void viewDidLoad() {
        exitButton.setOnAction(event -> {
            close();
            Injectable.getIntance().find(LoginView.class).openWindow();
        });
    }

    @Override
    public void viewDidClose() {

    }

    @Override
    public VBox getRoot() {
        return (VBox)root;
    }

}
