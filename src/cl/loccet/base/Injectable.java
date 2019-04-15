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
public final class Injectable {

    private static final HashMap<Class, Instance> vistas = new HashMap<>();

    private static Stage primaryStage;

    public static <T extends Component> T find(Class<T> type) {
        return find(type, null);
    }

    public static <T extends Component> T find(Class<T> type, String path) {
        if (vistas.containsKey(type))
            return (T) vistas.get(type);

        Component result = getOrCreate(type, path);

//        if (UIComponent.class.isAssignableFrom(result.getClass()))
//            ((UIComponent)result).viewDidLoad();

        return (T) result;
    }

    private static <T extends Component> T getOrCreate(Class<T> type, String path) {
        T injectable;

        if (Instance.class.isAssignableFrom(type)) {
            if (!vistas.containsKey(type)) {
                try {
                    injectable = type.newInstance();

                    if (UIComponent.class.isAssignableFrom(injectable.getClass())) {
                        UIComponent component = (UIComponent) injectable;

                        component.loadFXML(path);
                        component.init();
                    }

                    vistas.put(type, (Instance) injectable);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            injectable = (T) vistas.get(type);

            return injectable;
        }

        try {
            injectable = type.newInstance();

            if (UIComponent.class.isAssignableFrom(injectable.getClass())) {
                UIComponent component = (UIComponent) injectable;

                component.loadFXML(path);
                component.init();
            }
            // Fragment
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return injectable;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Injectable.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
