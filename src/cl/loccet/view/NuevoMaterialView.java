package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NuevoMaterialView extends View {

    private InventarioMaterialController controller;
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }


    @FXML
    private Button agregarBT;
    @FXML
    private Button cancelarTB;

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

    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    public void nuevoMaterial(ActionEvent event) {
        Material material = new Material(nombreTF.getText(), descripcionTF.getText(), Integer.parseInt(cantidadTF.getText()), "hola");
        controller.agregarMaterial(material);
        close();
    }

    public void salir(ActionEvent event){
        nombreTF.setText("");
        descripcionTF.setText("");
        cantidadTF.setText("");
        close();
    }

}

