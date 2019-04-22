package view;

import base.Fragment;
import controller.InventarioMaterialController;
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

    private InventarioMaterialController controller;

    private String idMaterial;

    @FXML
    private Button agregarBT;

    @FXML
    private Button cancelarBT;

    @FXML
    private TextField agregarTF;

    @Override
    public void viewDidLoad() {
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        agregarTF.setTextFormatter(formatter);
    }

    public void setIdMaterial(String idMaterial){
        this.idMaterial = idMaterial;
    }

    /**
     * Evento del boton para agregar mas cantidad al material
     * @param event
     */
    @FXML
    public void cantidadItem(ActionEvent event){
        try {
            controller.agregarMaterial(idMaterial, Double.parseDouble(agregarTF.getText()));
            close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar un numero");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancelar(ActionEvent event){
        close();
    }

    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

}
