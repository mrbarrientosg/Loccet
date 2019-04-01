package cl.loccet.base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;


/**
 * Subclase de Component, en donde contiene las acciones para poder cargar los fxml,
 * ademas contiene las transiciones de las vistas.
 *
 * @author Matias Barrientos
 */
public abstract class UIComponent extends Component {

    private FXMLLoader fxmlLoader;

    protected Parent root;

    private Stage modalStage;

    protected Window getCurrentWindow() {
        if (modalStage != null)
            return modalStage;

        if (getRoot().getScene() != null && getRoot().getScene().getWindow() != null)
            return getRoot().getScene().getWindow();

        if (Router.getIntance().getPrimaryStage() != null)
            return Router.getIntance().getPrimaryStage();

        return null;
    }

    public Stage getCurrentStage() {
        if (getCurrentWindow() == null)
            return null;

        return (Stage) getCurrentWindow();
    }

    public abstract Parent getRoot();

    public <T extends Node> T loadFXML() {
        return loadFXML(null, false, null);
    }

    public <T extends Node> T loadFXML(String ruta) {
        return loadFXML(ruta, false, null);
    }

    public <T extends Node> T loadFXML(String ruta, boolean atributoControlador, Object raiz) {
        URL fxmlUrl = locateFXML(ruta);

        if (fxmlUrl == null)
            LOGGER.log(Level.SEVERE, "URL fxml nulo");

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);

        if (raiz != null) fxmlLoader.setRoot(raiz);

        if (atributoControlador)
            fxmlLoader.setControllerFactory(param -> {  return this; });
        else
            fxmlLoader.setController(this);

        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error cargar fxml", e);
        }

        return null;
    }

    private URL locateFXML(String ruta) {
        String loc;

        if (ruta == null)
            loc = "../resource/fxml/" + this.getClass().getSimpleName().replaceAll("View", "").toLowerCase() + ".fxml";
        else
            loc = ruta;

        return getResources().url(loc);
    }

    public Stage openWindow() {
        return openModal(StageStyle.DECORATED, Modality.NONE, getCurrentWindow(), false, false);
    }

    public Stage openModal() {
        return openModal(StageStyle.DECORATED, Modality.APPLICATION_MODAL, getCurrentWindow(), false, false);
    }

    public Stage openModal(StageStyle stageStyle, Modality modality, Window owner, boolean resizable, boolean block) {
        if (modalStage == null) {
            modalStage = new Stage(stageStyle);

            modalStage.initModality(modality);
            modalStage.setResizable(resizable);
            //if (owner != null) modalStage.initOwner(owner);

//            modalStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//                if (event.getCode() == KeyCode.ESCAPE) {
//                    close();
//                    LOGGER.info("Aqui");
//                }
//            });

            if (getRoot().getScene() != null) {
                modalStage.setScene(getRoot().getScene());
            } else {
                modalStage.setScene(new Scene(getRoot()));
            }

            modalStage.setOnShown(event -> {
                modalStage.setX(getCurrentWindow().getX() + (getCurrentWindow().getWidth() / 2) - (getCurrentWindow().getWidth() / 2));
                modalStage.setY(getCurrentWindow().getY() + (getCurrentWindow().getWidth() / 2) - (getCurrentWindow().getWidth() / 2));
            });

            modalStage.setOnHidden(event -> {
                modalStage = null;
            });

            if (block)
                modalStage.showAndWait();
            else
                modalStage.show();
        } else {
            if (!modalStage.isShowing())
                if (block)
                    modalStage.showAndWait();
                else
                    modalStage.show();
        }

        return modalStage;
    }

    public void close() {
        if (modalStage != null) {
            modalStage.close();
            modalStage = null;
            return;
        }

        if (getCurrentStage() != null) {
            getCurrentStage().close();
        }
    }
}
