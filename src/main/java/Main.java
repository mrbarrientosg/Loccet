import base.Injectable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import router.LoginRouter;
import view.LoginView;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Injectable.setPrimaryStage(primaryStage);

        LoginView loginView = LoginRouter.create();

        Scene scene = new Scene(loginView.getRoot());

        primaryStage.setResizable(false);
        primaryStage.titleProperty().bind(loginView.getTitleProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
