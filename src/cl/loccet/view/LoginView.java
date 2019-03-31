package cl.loccet.view;

import cl.loccet.base.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginView extends View {

    @FXML
    private TextField rutField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @Override
    protected void init() {
        getLOGGER().info("Init");

        exitButton.setCancelButton(true);
        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
    }

    private void login(ActionEvent actionEvent) {
        System.out.println(rutField.getText());
        System.out.println(passwordField.getText());
    }

    private void exit(ActionEvent actionEvent) {
        // TODO: Salir del login
    }
}
