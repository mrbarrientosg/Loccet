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

public abstract class UIComponent extends Component {

    private FXMLLoader fxmlLoader;

    protected Parent root;

    private Stage modalStage;

    protected Window getCurrentWindow() {
        if (modalStage != null)
            return modalStage;
        else {
            try {
                if (getRoot().getScene().getWindow() != null) {
                    return getRoot().getScene().getWindow();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        return getPrimaryStage();
    }

    public Stage getCurrentStage() {
        return (Stage) getCurrentWindow();
    }

    public abstract Parent getRoot() throws IOException;

    public <T extends Node> T loadFXML() throws IOException {
        return loadFXML(null, false, null);
    }

    public <T extends Node> T loadFXML(String ruta) throws IOException {
        return loadFXML(ruta, false, null);
    }

    public <T extends Node> T loadFXML(String ruta, boolean atributoControlador, Object raiz) throws IOException {
        URL fxmlUrl = locateFXML(ruta);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);

        if (raiz != null) fxmlLoader.setRoot(raiz);

        if (atributoControlador)
            fxmlLoader.setControllerFactory(param -> {  return this; });
        else
            fxmlLoader.setController(this);

        return fxmlLoader.load();
    }

    private URL locateFXML(String ruta) {
        String loc;

        if (ruta == null)
            loc = "../resource/fxml/" + this.getClass().getSimpleName().replaceAll("View", "").toLowerCase() + ".fxml";
        else
            loc = ruta;

        return getResources().url(loc);
    }

    public Stage showModal(StageStyle stageStyle, Modality modality, boolean resizable, boolean block) throws IOException {
        if (modalStage == null) {
            modalStage = new Stage(stageStyle);

            modalStage.initModality(modality);
            modalStage.setResizable(resizable);
            //modalStage.initOwner(getCurrentWindow());

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
                System.gc();
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
        if (modalStage != null || getCurrentStage() != null) {
            modalStage = null;
        }
        getCurrentStage().close();
    }
}
