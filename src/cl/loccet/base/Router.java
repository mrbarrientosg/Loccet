package cl.loccet.base;

import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Clase que guarda la referencia de las vistas principales,
 * las vistas como las alertas u otro tipo no van a ir aqui.
 *
 * El proposito de esta clase es mantener las referencias y
 * no tener que crear las vistas cada vez que se muestra por
 * pantalla, asi teniendo una gran mejora en tiempo.
 *
 * @author Matias Barrientos
 */
public class Router {

    private static Router instance;

    private final HashMap<Class, Injectable> vistas;

    private Stage primaryStage;

    private Router() {
        vistas = new HashMap<>();
    }

    public <T extends Component> T find(Class<T> type) {
        if (!vistas.containsKey(type)) {
            Object cmp;

            try {
                cmp = type.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            ((UIComponent)cmp).viewDidLoad();

            vistas.put(type, (Injectable) cmp);
        }

        return (T) vistas.get(type);
    }

    public <T extends View> T getView(RouterView view) {
        return (T) vistas.get(view);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Router getIntance() {
        if (instance == null)
            instance = new Router();

        return instance;
    }
}
