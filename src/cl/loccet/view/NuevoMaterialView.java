package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class NuevoMaterialView extends View {

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
        unidadesDeMedida.add("CM");
        unidadesDeMedida.add("CM2");
        unidadesDeMedida.add("CM3");
        unidadesDeMedida.add("M");
        unidadesDeMedida.add("M2");
        unidadesDeMedida.add("M3");
        unidadesDeMedida.add("L");
        unidadCB.setItems(unidadesDeMedida);
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

    @FXML
    public void nuevoMaterial(ActionEvent event) {
        String lector = idMaterialTF.getText();
        Material material;
        try {
            if (lector.isEmpty())
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario no ingresa el id
                        unidadCB.getSelectionModel().getSelectedItem().toString(),                                             //se utiliza este constructor.
                        Double.parseDouble(precioTF.getText()));

            else
                material = new Material(nombreTF.getText(), descripcionTF.getText(), Double.parseDouble(cantidadTF.getText()), //Si el usuario si ingresa id
                        unidadCB.getSelectionModel().getSelectedItem().toString(), lector,                                     //Se utiliza este constructor.
                        Double.parseDouble(precioTF.getText()));

            controller.nuevoMaterial(material);
            close();
        } catch (Exception e){//En caso de que el usuario deje un campo vacio salta una excepcion.
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

