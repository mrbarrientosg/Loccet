package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginView extends View {

    private LoginController controller;

    @FXML
    private TextField rutField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    public LoginView() {
        super("Inicio sesion");
    }

    @Override
    public void viewDidLoad() {
        rutField.requestFocus();

        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);

        controller.rutProperty().bind(rutField.textProperty());
        controller.passwordProperty().bind(passwordField.textProperty());
    }

    @Override
    public void viewDidClose() {
        clear();
    }

    private void login(ActionEvent actionEvent) {
        controller.loginUser();
    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    private void clear() {
        rutField.textProperty().set("");
        passwordField.textProperty().set("");

        controller.rutProperty().unbind();
        controller.passwordProperty().unbind();
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }
}
