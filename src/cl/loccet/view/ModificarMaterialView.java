package cl.loccet.view;

import cl.loccet.base.Fragment;
import cl.loccet.controller.InventarioMaterialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Vista de modificar material.
 *
 * @author Sebastian Fuenzalida.
 */
public class ModificarMaterialView extends Fragment {

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

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewDidClose() {
        modificarIdTF.setText("");
        modificarDescripcionTA.setText("");
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
            close();
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
