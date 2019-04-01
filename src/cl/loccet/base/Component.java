package cl.loccet.base;

import cl.loccet.resource.ResourceUtil;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Clase base que contiene lo necesario para un controlador y padre de la clase
 * UIComponent
 *
 * @author Matias Barrientos
 */
public abstract class Component {

    protected Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private ResourceUtil resources;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    protected ResourceUtil getResources() {
        if (resources == null)
            resources = new ResourceUtil(this);

        return resources;
    }
}
