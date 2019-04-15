package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




public class AgregarMaterialView extends View {

    private InventarioMaterialController controller;
    private Material material;
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }


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
    public void viewDidClose() {

    }

    private static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    @FXML
    public void cantidadItem(ActionEvent event){
        String lector = agregarTF.getText();
        if (!isNumeric(lector)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar un numero");
            alert.showAndWait();
        }
        else{
            controller.agregarMaterial(material,Integer.parseInt(lector));
            close();
        }
    }

    public void cancelar(ActionEvent event){
        agregarTF.setText("");
        close();
    }


}