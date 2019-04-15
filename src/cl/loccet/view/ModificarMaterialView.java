package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ModificarMaterialView extends View {

    @FXML
    private TextField modificarIdTF;
    @FXML
    private TextArea modificarDescripcionTA;
    @FXML
    private Button aceptarTB;
    @FXML
    private Button cancelarBT;


    private InventarioMaterialController controller;
    private String idMaterial;
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }
    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewDidClose() {

    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    @FXML public void modificar(ActionEvent event){
        String nombre = modificarIdTF.getText();
        String descripcion =modificarDescripcionTA.getText();
        if (nombre.isEmpty() && descripcion.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar al menos un campo");
            alert.showAndWait();

        }
        else {
            if (!nombre.isEmpty()) controller.modificarNombre(idMaterial,nombre);
            if (!descripcion.isEmpty()) controller.modificarDescripcion(idMaterial,descripcion);
            modificarIdTF.setText("");
            modificarDescripcionTA.setText("");
            close();
        }
    }

   @FXML public void cancelar(ActionEvent event){
        modificarDescripcionTA.setText("");
        modificarIdTF.setText("");
        close();
    }

}