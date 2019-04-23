package view;


import base.View;
import cell.MaterialCell;
import controller.InventarioMaterialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import model.Material;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


/**
 * Vista de Retirar material.
 *
 * @author Sebastian Fuenzalida.
 */
public final class RetirarMaterialView extends View {

    private InventarioMaterialController controller;

    private MaterialCell material;

    @FXML
    private Button retirarBT;

    @FXML
    private Button cancelarBT;

    @FXML
    private TextField retirarTF;

    @Override
    public void viewDidLoad() {
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        retirarTF.setTextFormatter(formatter);
    }

    @Override
    public void viewDidClose() {
        retirarTF.setText("");
    }

    public void setMaterial(MaterialCell material){
        this.material = material;
    }

    @FXML
    public void cantidadItem(ActionEvent event){
       if(!retirarTF.getText().isEmpty()) {
           String lector = retirarTF.getText();
           double aux = Double.parseDouble(lector);
           if (Double.compare(aux,material.getCantidad())>0) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("No hay suficiente material");
               alert.setContentText("La cantidad de material a retirar es mayor al que se tiene");
               alert.showAndWait();
           } else {
               controller.retirarMaterial(material.getId(), aux);
               close();
           }
       }else {
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
