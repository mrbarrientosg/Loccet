package base;

import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Clase base que contiene lo necesario para un controlador y padre de la clases UIComponent
 *
 * @author Matias Barrientos
 */
public abstract class Component {

    protected Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private ResourceUtil resources;

    public Stage getPrimaryStage() {
        return Injectable.getPrimaryStage();
    }

    protected ResourceUtil getResources() {
        if (resources == null)
            resources = new ResourceUtil(this);

        return resources;
    }
}
