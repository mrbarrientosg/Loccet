package cl.loccet.view;

import cl.loccet.base.Router;
import cl.loccet.base.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;

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
    public VBox getRoot() throws IOException {
        if (root == null)
            root = loadFXML();
        return (VBox) root;
    }

    @Override
    protected void init() {
        getLOGGER().info(this.toString());

        exitButton.setCancelButton(true);
        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
    }

    private void login(ActionEvent actionEvent) {
        System.out.println(rutField.getText());
        System.out.println(passwordField.getText());
        close();
    }

    private void exit(ActionEvent actionEvent) {
        // TODO: Salir del login
        LoginView l = new LoginView();
        try {
            l.showModal(StageStyle.DECORATED, Modality.APPLICATION_MODAL, false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
