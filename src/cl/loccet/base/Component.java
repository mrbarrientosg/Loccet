package cl.loccet.base;

import cl.loccet.resource.ResourceUtil;
import javafx.stage.Stage;

import java.util.logging.Logger;

public abstract class Component {

    private Logger LOGGER;

    private ResourceUtil resources;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    protected Logger getLOGGER() {
        if (LOGGER == null)
            LOGGER = Logger.getLogger(this.getClass().getName());

        return LOGGER;
    }

    protected ResourceUtil getResources() {
        if (resources == null)
            resources = new ResourceUtil(this);

        return resources;
    }
}
