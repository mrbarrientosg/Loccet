package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NuevoMaterialView extends View {

    private InventarioMaterialController controller;
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }
    private ObservableList unidadesDeMedida;

    @FXML
    private Button agregarBT;
    @FXML
    private Button cancelarTB;

    @FXML
    private TextField idMaterialTF;
    @FXML
    private TextField nombreTF;
    @FXML
    private TextArea descripcionTF;
    @FXML
    private TextField cantidadTF;


    @FXML
    private ComboBox unidadCB;

    @Override
    public void viewDidLoad() {
        unidadesDeMedida = FXCollections.observableArrayList();
        unidadesDeMedida.add("CM");
        unidadesDeMedida.add("CM2");
        unidadesDeMedida.add("CM3");
        unidadesDeMedida.add("M");
        unidadesDeMedida.add("M2");
        unidadesDeMedida.add("M3");
        unidadesDeMedida.add("L");
        unidadCB.setItems(unidadesDeMedida);
        cantidadTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cantidadTF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    public void nuevoMaterial(ActionEvent event) {
        String lector = idMaterialTF.getText();
        Material material;
        try {
            if (lector.isEmpty())
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Integer.parseInt(cantidadTF.getText()), unidadCB.getSelectionModel().getSelectedItem().toString());
            else
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Integer.parseInt(cantidadTF.getText()), unidadCB.getSelectionModel().getSelectedItem().toString(), lector);

            controller.nuevoMaterial(material);
            clear();
            close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar los caampos requeridos");
            alert.showAndWait();
        }

    }
    private void clear(){
        nombreTF.setText("");
        descripcionTF.setText("");
        cantidadTF.setText("");
    }
    public void salir(ActionEvent event){
        clear();
        close();
    }

}

