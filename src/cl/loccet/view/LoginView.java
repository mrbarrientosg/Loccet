package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.LoginController;
import cl.loccet.model.Constructora;
import cl.loccet.router.HomeRouter;
import cl.loccet.util.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class LoginView extends View {

    private LoginController controller;

    @FXML
    private GridPane gridPane;

    @FXML
    private RowConstraints separatorRow;

    @FXML
    private RowConstraints proyectoRow;

    @FXML
    private RadioButton radioConstructora;

    @FXML
    private RadioButton radioContratista;

    @FXML
    private ChoiceBox<String> listProyecto;

    @FXML
    private TextField rutField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private ToggleGroup userToggleGroup;

    @FXML
    private HBox proyectoHbox;

    public LoginView() {
        super("Inicio sesion");
    }

    @Override
    public void viewDidLoad() {
        proyectoRow.setMaxHeight(0);
        proyectoHbox.setVisible(false);

        userToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == radioContratista) {
                proyectoRow.setMaxHeight(-1);
                proyectoHbox.setVisible(true);
            } else {
                proyectoRow.setMaxHeight(0);
                proyectoHbox.setVisible(false);
            }
        });

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
