package cl.loccet;

import cl.loccet.view.LoginView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView loginView = new LoginView();

        Parent view = loginView.loadFXML("../resource/fxml/login.fxml");

        Scene scene = new Scene(view);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
