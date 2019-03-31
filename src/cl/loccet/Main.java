package cl.loccet;

import cl.loccet.base.Router;
import cl.loccet.base.RouterView;
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
        initRouter(primaryStage);

        LoginView loginView = Router.getIntance().getView(RouterView.LOGIN);

        Parent view = loginView.loadFXML();

        Scene scene = new Scene(view);

        Router.getIntance().setRoot(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initRouter(Stage primaryStage) {
        Router r = Router.getIntance();
        r.setCurrentStage(primaryStage);
        r.addView(RouterView.LOGIN, new LoginView());
    }
}
