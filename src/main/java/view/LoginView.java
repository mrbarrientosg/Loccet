package view;

import base.Fragment;
import base.Injectable;
import controller.LoginController;
import exceptions.EmptyFieldException;
import exceptions.InvalidUserException;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.Alert;

public final class LoginView extends Fragment {

    private LoginController controller;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField dns;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button exitRedButton;

    private double xOffset = 0;

    private double yOffset = 0;

    private BooleanProperty disable;

    @Override
    public void viewDidLoad() {
        controller = new LoginController();
        controller.setView(this);

        disable = new SimpleBooleanProperty(false);

        getRoot().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        getRoot().setOnMouseDragged(event -> {
            if (yOffset <= 50) {
                getPrimaryStage().setX(event.getScreenX() - xOffset);
                getPrimaryStage().setY(event.getScreenY() - yOffset);
            }
        });

        username.disableProperty().bind(disable);
        password.disableProperty().bind(disable);
        dns.disableProperty().bind(disable);
        loginButton.disableProperty().bind(disable);
        exitButton.disableProperty().bind(disable);

        loginButton.setOnAction(this::login);

        exitRedButton.setOnAction(this::exit);
        exitButton.setOnAction(this::exit);

        minimizeButton.setOnAction(this::minimize);
    }

    @Override
    public void viewDidClose() {
        controller.clear();
    }

    private void login(ActionEvent actionEvent) {
        try {
            controller.loginUser();
        } catch (EmptyFieldException e) {
            onError(e);
        }
    }

    public void didLogin() {
        controller.loadData();
    }

    public void showLoading() {
        disable.setValue(true);

        LOGGER.info("Cargando");
        loadingOverlay();
    }

    public void hideLoading() {
        disable.setValue(false);
        LOGGER.info("Listo");
        getRoot().getChildren().remove(1);
    }

    public void gotoHome() {
        TableroView tableroView = Injectable.find(TableroView.class);
        replaceWith(tableroView, true, true);
    }

    public void onError(Throwable e) {
        if (e instanceof EmptyFieldException || e instanceof InvalidUserException) {
            Alert.error()
                    .withDescription(e.getMessage())
                    .withButton(ButtonType.OK)
                    .build().show();
        } else {
            Alert.error()
                    .withDescription("Opps ha ocurrido un error, intenta de nuevo.")
                    .withButton(ButtonType.OK)
                    .build().show();
        }
    }

    private void minimize(ActionEvent actionEvent) {
        getPrimaryStage().setIconified(true);
    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public StackPane getRoot() {
        return (StackPane) root;
    }

    private void loadingOverlay() {
        ProgressIndicator pi = new ProgressIndicator();
        VBox box = new VBox(pi);
        box.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        box.setAlignment(Pos.CENTER);
        getRoot().getChildren().add(box);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getDNS() {
        return dns.getText();
    }
}
