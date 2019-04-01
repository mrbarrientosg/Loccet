package cl.loccet.base;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class View extends UIComponent implements Initializable {

    // TODO: Implementar funcion para poder saber cuando se cierra una vista o se abre.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    protected abstract void init();

}
