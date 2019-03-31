package cl.loccet.base;

import cl.loccet.resource.ResourceUtil;
import java.util.logging.Logger;

public abstract class Component {

    private Logger LOGGER;

    private ResourceUtil resources;

    public Component() { }

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
