package cl.loccet;

import cl.loccet.base.Injectable;
import cl.loccet.model.InventarioMaterial;
import cl.loccet.router.InventarioMaterialRouter;
import cl.loccet.view.InventarioMaterialView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Injectable.setPrimaryStage(primaryStage);

        InventarioMaterial inventario = new InventarioMaterial();

        InventarioMaterialView inventarioView = InventarioMaterialRouter.create(inventario);

        Scene scene = new Scene(inventarioView.getRoot());

        primaryStage.setResizable(false);
        primaryStage.titleProperty().bind(inventarioView.getTitleProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
