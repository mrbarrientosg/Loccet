package view;

import base.Fragment;
import controller.LoginController;
import exceptions.EmptyFieldsException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import network.DispatchQueue;
import network.NetworkException;
import router.LoginRouter;
import util.FakeData;

import java.io.IOException;

public final class LoginView extends Fragment {

    private LoginController controller;

    private LoginRouter router;

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

        loginButton.setOnAction(this::login);

        exitRedButton.setOnAction(this::exit);
        exitButton.setOnAction(this::exit);

        minimizeButton.setOnAction(this::minimize);
    }

    @Override
    public void viewDidClose() {

    }

    private void login(ActionEvent actionEvent) {
        new DispatchQueue<>(dispatchQueue -> {
            try {
                return controller.loginUser();
            } catch (EmptyFieldsException e) {
                onError(e.getMessage());
            } catch (IOException e) {
                onError("Opss ha ocurrido un error, vuelve a intentar");
            } catch (NetworkException e) {
                onError(e.getMessage());
            }

            return false;
        }).success(login -> {
            if (login) {
                didLogin();
            } else {
                //onError("Verifique el usuario o contraseña son correctos. Valide que su DNS sea el correcto");
            }
        });
    }

    public void didLogin() {
        TableroView tableroView = router.showTablero(FakeData.createFakeData());
        replaceWith(tableroView, true, true);
    }

    public void onError(String message){
        router.showError(message);
    }

    private void minimize(ActionEvent actionEvent) {
        getPrimaryStage().setIconified(true);
    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    public void setRouter(LoginRouter router) {
        this.router = router;
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
