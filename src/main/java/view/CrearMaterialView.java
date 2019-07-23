package view;

import base.View;
import controller.InventarioMaterialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Material;
import model.RegistroMaterial;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public final class CrearMaterialView extends View {

    private InventarioMaterialController controller;

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
    private TextField precioTF;

    @FXML
    private ComboBox<String> unidadCB;

    @Override
    public void viewDidLoad() {
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change ->
                 pattern.matcher(change.getControlNewText()).matches() ? change : null);

        cantidadTF.setTextFormatter(formatter);

        TextFormatter formater =  new TextFormatter<UnaryOperator>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null );

        precioTF.setTextFormatter(formater);

    }

    @Override
    public void viewDidShow() {
        unidadCB.setItems(controller.fetchUnidades());
        unidadCB.getSelectionModel().selectFirst();
    }

    @Override
    public void viewDidClose() {
        clear();//Limpia los TextField.
    }

    private RegistroMaterial registroMaterial(double cantidad){
        return new RegistroMaterial(cantidad,false);
    }

    @FXML
    public void agregar(ActionEvent event) {
        String lector = idMaterialTF.getText();
        Material material;
        RegistroMaterial registroMaterial;
        if (!nombreTF.getText().isEmpty() && !descripcionTF.getText().isEmpty()&& !cantidadTF.getText().isEmpty()
        && !precioTF.getText().isEmpty()){
            if (lector.isEmpty()) {
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario no ingresa el id
                        unidadCB.getSelectionModel().getSelectedItem(),                                             //se utiliza este constructor.
                        BigDecimal.valueOf(Double.parseDouble(precioTF.getText())));
            } else{
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario si ingresa id
                        unidadCB.getSelectionModel().getSelectedItem(), lector,                                     //Se utiliza este constructor.
                        BigDecimal.valueOf(Double.parseDouble(precioTF.getText())));
            }

            registroMaterial = registroMaterial(material.getCantidad());

            controller.nuevoMaterial(material, registroMaterial);

            close();
        } else{//En caso de que el usuario deje un campo vacio salta una excepcion.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Ingreso de datos invalido");
            alert.setContentText("Por favor ingresar los campos requeridos");
            alert.showAndWait();
        }

    }
    private void clear(){
        idMaterialTF.setText("");
        nombreTF.setText("");
        descripcionTF.setText("");
        cantidadTF.setText("");
        precioTF.setText("");
    }

    @FXML
    public void salir(ActionEvent event){
        close();
    }

    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

}

