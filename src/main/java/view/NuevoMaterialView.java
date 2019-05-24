package view;

import base.View;
import controller.InventarioMaterialController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Material;
import model.RegistroMaterial;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public final class NuevoMaterialView extends View {

    private InventarioMaterialController controller;

    private ObservableList<String> unidadesDeMedida;

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
        unidadesDeMedida = FXCollections.observableArrayList();
        unidadesDeMedida.add("MM");
        unidadesDeMedida.add("CM");
        unidadesDeMedida.add("CM2");
        unidadesDeMedida.add("CM3");
        unidadesDeMedida.add("M");
        unidadesDeMedida.add("M2");
        unidadesDeMedida.add("M3");
        unidadesDeMedida.add("L");
        unidadesDeMedida.add("KG");
        unidadesDeMedida.add("GR");
        unidadesDeMedida.add("UN");
        unidadCB.setItems(unidadesDeMedida);
        unidadCB.getSelectionModel().selectFirst();
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> {            //Permite agregar solo numeros y un punto.
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        cantidadTF.setTextFormatter(formatter);

        Pattern patern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formater =  new TextFormatter<UnaryOperator>(change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        precioTF.setTextFormatter(formater);

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
        if (!nombreTF.getText().isEmpty() && !descripcionTF.getText().isEmpty()&& !cantidadTF.getText().isEmpty()
        && !precioTF.getText().isEmpty()){
            if (lector.isEmpty()) {
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario no ingresa el id
                        unidadCB.getSelectionModel().getSelectedItem().toString(),                                             //se utiliza este constructor.
                        BigDecimal.valueOf(Double.parseDouble(precioTF.getText())));
                material.agregarRegistro(registroMaterial(material.getCantidad()));

            } else{
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario si ingresa id
                        unidadCB.getSelectionModel().getSelectedItem().toString(), lector,                                     //Se utiliza este constructor.
                        BigDecimal.valueOf(Double.parseDouble(precioTF.getText())));
                material.agregarRegistro(registroMaterial(material.getCantidad()));

            }

            controller.nuevoMaterial(material);
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

