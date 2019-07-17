package view;

import base.Fragment;
import controller.DetalleMaterialController;
import controller.InventarioMaterialController;
import exceptions.NegativeQuantityException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * Vista de agregar material.
 *
 * @author Sebastian Fuenzalida.
 */

public final class AgregarMaterialView extends Fragment {

    private DetalleMaterialController controller;

    @FXML
    private Button agregarBT;

    @FXML
    private Button cancelarBT;

    @FXML
    private TextField agregarTF;

    @Override
    public void viewDidLoad() {

    }
    @Override
    public void viewDidShow(){
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        agregarTF.setTextFormatter(formatter);
    }

    /**
     * Evento del boton para agregar mas cantidad al material
     * @param event
     */
    @FXML
    public void cantidadItem(ActionEvent event){
        if(!agregarTF.getText().isEmpty()) {
            try {
                controller.agregarMaterial(Double.parseDouble(agregarTF.getText()));
            } catch (NegativeQuantityException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar un numero");
            alert.showAndWait();
        }
    }

    @FXML
    public void salir(ActionEvent event){
        close();
    }

    public void setController(DetalleMaterialController controller) {
        this.controller = controller;
    }

}