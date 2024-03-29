import base.Injectable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import network.service.NetService;
import util.ThreadPools;
import view.LoginView;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("./fonts/Heebo-Black.ttf"), 0);
        Font.loadFont(getClass().getResourceAsStream("./fonts/Heebo-Light.ttf"), 0);
        Font.loadFont(getClass().getResourceAsStream("./fonts/Heebo-Medium.ttf"), 0);
        Font.loadFont(getClass().getResourceAsStream("./fonts/Heebo-Regular.ttf"), 0);

        Injectable.setPrimaryStage(primaryStage);

        LoginView loginView = Injectable.find(LoginView.class);

        Scene scene = new Scene(loginView.getRoot());
        primaryStage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);

        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        NetService.getInstance().close();
        ThreadPools.getInstance().shutdownThreadPools();
    }
}

