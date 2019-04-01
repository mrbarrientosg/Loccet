package cl.loccet.base;

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
