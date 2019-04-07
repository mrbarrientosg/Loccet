package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.AgregarProyectoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Matias Zuñiga
 * Establece la vista de agregar proyecto al momento de presionarlo en el menuBar
 */

public class AgregarProyectoView extends View {

    private AgregarProyectoController controller;

    //Botones

    @FXML private Button seleccionarTrabajadores;
    @FXML private Button aceptar;
    @FXML private Button cancelar;

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewDidClose() {

    }
    public void setController(AgregarProyectoController controller) {
        this.controller = controller;
    }
}
