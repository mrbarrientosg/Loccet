package view;

import base.Fragment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public final class LoginView extends Fragment {

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

    @Override
    public void viewDidLoad() {

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
    }

    @Override
    public void viewDidClose() {
        getRoot().onMousePressedProperty().set(null);
        getRoot().onMouseDraggedProperty().set(null);
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

    public void setLoginButtonAction(EventHandler<ActionEvent> eventHandler) {
        loginButton.setOnAction(eventHandler);
    }

    public void setMinimizeButtonAction(EventHandler<ActionEvent> eventHandler) {
        minimizeButton.setOnAction(eventHandler);
    }

    public void setExitButtonAction(EventHandler<ActionEvent> eventHandler) {
        exitButton.setOnAction(eventHandler);
        exitRedButton.setOnAction(eventHandler);
    }
}
