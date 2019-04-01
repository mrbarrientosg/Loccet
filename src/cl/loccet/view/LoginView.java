package cl.loccet.view;

import cl.loccet.base.Router;
import cl.loccet.base.RouterView;
import cl.loccet.base.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    public VBox getRoot() {
        if (root == null)
            root = loadFXML();
        return (VBox) root;
    }

    @Override
    protected void init() {
        LOGGER.info(this.toString());

        exitButton.setCancelButton(true);
        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
    }

    private void login(ActionEvent actionEvent) {
        LOGGER.info("USUARIO: " + rutField.getText());
        LOGGER.info("CONSTRASEÑA: " + passwordField.getText());

        Router.getIntance().getView(RouterView.HOME).openModal();
        close();
    }

    private void exit(ActionEvent actionEvent) {
        // TODO: Salir del login
    }
}
