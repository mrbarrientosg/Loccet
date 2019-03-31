package cl.loccet.base;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class Router {

    private static Router instance;

    private final HashMap<RouterView, View> vistas;

    private Router() {
        vistas = new HashMap<>();
    }

    public void addView(RouterView view, View instance) {
        vistas.put(view, instance);
    }

    public <T extends View> T getView(RouterView view) {
        return (T) vistas.get(view);
    }

    public static Router getIntance() {
        if (instance == null)
            instance = new Router();
        return instance;
    }
}
