package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.model.Constructora;
import cl.loccet.router.HomeRouter;
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
        return (VBox) root;
    }

    public LoginView() {
        super("Inicio sesion");
    }

    @Override
    public void viewDidLoad() {
        LOGGER.info(this.toString());

        exitButton.setCancelButton(true);
        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
    }

    @Override
    public void viewDidClose() {
        rutField.setText("");
        passwordField.setText("");
    }

    private void login(ActionEvent actionEvent) {
        LOGGER.info("USUARIO: " + rutField.getText());
        LOGGER.info("CONSTRASEÑA: " + passwordField.getText());

        // TODO: implementar el controlador para poder gestionar la constructora
        Constructora c = new Constructora("RUT","NOMBRE");
        HomeRouter.create(c).openWindow();
        close();
    }

    private void exit(ActionEvent actionEvent) {
        // TODO: Salir del login
    }
}
