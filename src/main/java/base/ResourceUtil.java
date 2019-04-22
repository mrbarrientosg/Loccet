package base;

import java.net.URL;

/**
 * Clase que simplifica la obtencion de recursos
 */
public final class ResourceUtil {

    private Object component;

    public ResourceUtil(Object component) {
        this.component = component;
    }

    public String get(String resource) {
        return component.getClass().getResource(resource).toExternalForm();
    }

    public URL url(String resource) {
        return component.getClass().getResource(resource);
    }
}
