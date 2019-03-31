package cl.loccet.base;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class Router {

    private static Router instance;

    private Stage currentStage;

    private Stage modalStage;

    private Scene root;

    private final HashMap<RouterView, View> vistas;

    private Router() {
        vistas = new HashMap<>();
    }

    public void addView(RouterView view, View instance) {
        vistas.put(view, instance);
    }

    public void setRoot(Scene root) {
        this.root = root;
    }

    public <T extends View> T getView(RouterView view) {
        return (T) vistas.get(view);
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Stage showModal(View view, StageStyle stageStyle, Modality modality, boolean resizable, boolean block) throws IOException {
        if (modalStage == null) {
            modalStage = new Stage();

            root.setRoot(view.loadFXML());
            modalStage.setScene(root);

            if (block)
                modalStage.showAndWait();
            else
                modalStage.show();
        } else {
            if (!modalStage.isShowing())
                modalStage.show();
        }

        return modalStage;
    }


    private void applyStage(StageStyle stageStyle, Modality modality, boolean resizable) {
        modalStage.initStyle(stageStyle);
        modalStage.initModality(modality);
        modalStage.setResizable(resizable);
    }

    public static Router getIntance() {
        if (instance == null)
            instance = new Router();
        return instance;
    }
}
