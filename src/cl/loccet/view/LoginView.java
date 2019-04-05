package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.model.Constructora;
import cl.loccet.router.HomeRouter;
import cl.loccet.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginView extends View {

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
        LOGGER.info(this.toString());

        exitButton.setCancelButton(true);
        exitButton.setOnAction(this::exit);

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
    }

    @Override
    public void viewDidClose() {
        System.out.println("Aqui");
        rutField.setText("");
        passwordField.setText("");
    }

    private void login(ActionEvent actionEvent) {
        System.out.println(Validator.of(rutField.getText(), passwordField.getText())
                .validate(name -> !name.isEmpty(), "ambos son vacios")
                .isValid());

        LOGGER.info("USUARIO: " + rutField.getText());
        LOGGER.info("CONSTRASEÑA: " + passwordField.getText());

        // TODO: implementar el controlador para poder gestionar la constructora
        Constructora c = new Constructora("RUT","NOMBRE");

        close();
        HomeRouter.create(c)
                .openWindow()
                .withResizable(true)
                .show();
    }

    private void exit(ActionEvent actionEvent) {
        // TODO: Salir del login
    }
}
