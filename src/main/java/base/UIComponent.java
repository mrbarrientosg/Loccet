package base;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
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

    private StringProperty titleProperty;

    private boolean isDocked;

    private boolean isInitialized = false;

    public UIComponent(String title) {
        titleProperty = new SimpleStringProperty(title);
    }

    public StringProperty getTitleProperty() {
        return titleProperty;
    }

    protected Window getCurrentWindow() {
        if (modalStage != null)
            return modalStage;

        if (getRoot().getScene() != null && getRoot().getScene().getWindow() != null)
            return getRoot().getScene().getWindow();

        if (getPrimaryStage() != null)
            return getPrimaryStage();

        return null;
    }

    public Stage getCurrentStage() {
        Stage value = (Stage) getCurrentWindow();

        if (value == null)
            LOGGER.warning("Error");

        return value;
    }

    public Parent getRoot() {
        return root;
    }

    public final void init() {
        if (isInitialized) return;

        root.parentProperty().addListener((observable, oldParent, newParent) -> {
            // Si la vista esta en un modalStage no se hace nada porque ya se llamo la funcion viewDidLoad
            if (modalStage != null) return;

            // La vista se ha eliminado de un parent (root) entonces se llama la funcion viewDidClose
            if (newParent == null && oldParent != null && isDocked) callOnUndock();

            // La vista a cambiado de padre (root), entonces se llama a la funcion viewDidLoad
            if (newParent != null && newParent != oldParent && !isDocked) callOnDock();
        });

        root.sceneProperty().addListener((observable, oldParent, newParent) -> {
            // Si la vista esta en un modalStage no se hace nada porque ya se llamo la funcion viewDidLoad
            if (modalStage != null || root.getParent() != null) return;

            // La vista se ha cerrado de una Scene entonces se llama la funcion viewDidClose
            if (newParent == null && oldParent != null && isDocked) callOnUndock();

            // La vista a cambiado de Scene, entonces se llama a la funcion viewDidLoad
            if (newParent != null && newParent != oldParent && !isDocked) {
                onChangeOnce(newParent.windowProperty(), window -> {
                    onChange(window.showingProperty(), it -> {
                        if (!it && isDocked) callOnUndock();
                        if (it && !isDocked) callOnDock();
                    });
                });

                callOnDock();
            }
        });

        isInitialized = true;
    }

    public abstract void viewDidLoad();

    public abstract void viewDidClose();

    public <T extends Node> T loadFXML() {
        return loadFXML(null);
    }

    public <T extends Node> T loadFXML(String ruta) {
        URL fxmlUrl = locateFXML(ruta);

        if (fxmlUrl == null)
            LOGGER.log(Level.SEVERE, "URL fxml nulo");

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);

       // if (raiz != null) fxmlLoader.setRoot(raiz);

        fxmlLoader.setController(this);

        /*if (atributoControlador)
            fxmlLoader.setControllerFactory(param -> {  return this; });
        else
            fxmlLoader.setController(this);*/

        try {
            root = fxmlLoader.load();
            return (T) root;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error cargar fxml", e);
        }

        return null;
    }

    private URL locateFXML(String ruta) {
        String loc;

        if (ruta == null)
            loc = "../fxml/" + this.getClass().getSimpleName().replaceAll("View", "").toLowerCase() + ".fxml";
        else
            loc = ruta;

        return getResources().url(loc);
    }

    public class ShowBuilder {

        private StageStyle stageStyle;
        private Modality modality;
        private Window owner;
        private boolean resizable;
        private boolean block;

        public ShowBuilder() {
            owner = getCurrentWindow();
        }

        public ShowBuilder withStyle(StageStyle stageStyle) {
            this.stageStyle = stageStyle;
            return this;
        }

        public ShowBuilder withModality(Modality modality) {
            this.modality = modality;
            return this;
        }

        public ShowBuilder withResizable(boolean resizable) {
            this.resizable = resizable;
            return this;
        }

        public ShowBuilder withBlock(boolean block) {
            this.block = block;
            return this;
        }

        public Stage show() {
            if (modalStage == null) {
                modalStage = new Stage();

                if (stageStyle != null) modalStage.initStyle(stageStyle);
                if (modality != null)  modalStage.initModality(modality);
                if (owner != null) modalStage.initOwner(owner);
                modalStage.setResizable(resizable);
                modalStage.titleProperty().bind(titleProperty);

                if (getRoot().getScene() != null) {
                    modalStage.setScene(getRoot().getScene());
                } else {
                    modalStage.setScene(new Scene(getRoot()));
                }

                modalStage.setOnShown(event -> {
                    modalStage.setX(getCurrentWindow().getX() + (getCurrentWindow().getWidth() / 2) - (getCurrentWindow().getWidth() / 2));
                    modalStage.setY(getCurrentWindow().getY() + (getCurrentWindow().getWidth() / 2) - (getCurrentWindow().getWidth() / 2));
                });

                modalStage.setOnShowing(event -> {
                    callOnDock();
                });

                modalStage.setOnHidden(event -> {
                    modalStage = null;
                    callOnUndock();
                });

                if (block)
                    modalStage.showAndWait();
                else
                    modalStage.show();

                return modalStage;
            } else {
                if (!modalStage.isShowing()) {
                    if (block)
                        modalStage.showAndWait();
                    else
                        modalStage.show();
                }
            }

            return modalStage;
        }
    }

    public ShowBuilder showBuilder() {
        return new ShowBuilder();
    }

    public ShowBuilder window() {
        return new ShowBuilder()
                .withStyle(StageStyle.DECORATED)
                .withModality(Modality.NONE)
                .withResizable(false)
                .withBlock(false);
    }

    public ShowBuilder modal() {
        return new ShowBuilder()
                .withStyle(StageStyle.DECORATED)
                .withModality(Modality.APPLICATION_MODAL)
                .withResizable(false)
                .withBlock(false);
    }


    private void callOnDock() {
        if (!isInitialized) init();
        isDocked = true;
        viewDidLoad();
    }

    private void callOnUndock() {
        isDocked = false;
        viewDidClose();
    }

    public void close() {
        if (modalStage != null) {
            modalStage.close();
        } else if (getCurrentStage() != null) {
            getCurrentStage().close();
        }

        modalStage = null;

    }

    private <T> void onChangeOnce(ReadOnlyObjectProperty<T> object, Consumer<T> op) {
        AtomicInteger counter = new AtomicInteger(0);

        ChangeListener<T> listener = new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                if (counter.incrementAndGet() == 1) {
                    object.removeListener(this);
                }
                op.accept(newValue);
            }
        };

        object.addListener(listener);
    }

    private <T> void onChange(ReadOnlyBooleanProperty object, Consumer<Boolean> op) {
        object.addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                op.accept(newValue);
            else
                op.accept(false);
        });
    }
}
