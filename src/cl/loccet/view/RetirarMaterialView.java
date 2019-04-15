package cl.loccet.view;
import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




public class RetirarMaterialView extends View {

    private InventarioMaterialController controller;
    private Material material;
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }


    @FXML
    private Button retirarBT;
    @FXML
    private Button cancelarBT;

    @FXML
    private TextField retirarTF;

    @Override
    public void viewDidLoad() {
        retirarTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                retirarTF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void viewDidClose() {

    }
    public void setMaterial(Material material){
        this.material = material;
    }

    @FXML
    public void cantidadItem(ActionEvent event){
       try {
           String lector = retirarTF.getText();
           int aux = Integer.parseInt(lector);
           if (aux > material.getCantidad()) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("No hay suficiente material");
               alert.setContentText("La cantidad de material a retirar es mayor al que se tiene");
               alert.showAndWait();
           } else {
               controller.retirarMaterial(material.getId(), aux);
               retirarTF.setText("");
               close();
           }
       }catch (Exception e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Ingreso de datos invalido");
           alert.setContentText("Por favor ingresar un numero");
           alert.showAndWait();
       }
    }

    public void cancelar(ActionEvent event){
        retirarTF.setText("");
        close();
    }


}
