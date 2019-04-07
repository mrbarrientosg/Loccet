package cl.loccet;

import cl.loccet.base.Injectable;
import cl.loccet.model.Especialidades;
import cl.loccet.router.LoginRouter;
import cl.loccet.view.ListaTrabajadorView;
import cl.loccet.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Especialidades.createFakeData();
        Injectable.setPrimaryStage(primaryStage);

        ListaTrabajadorView loginView = Injectable.find(ListaTrabajadorView.class);

        Scene scene = new Scene(loginView.getRoot());

        primaryStage.setResizable(true);
        primaryStage.titleProperty().bind(loginView.getTitleProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
