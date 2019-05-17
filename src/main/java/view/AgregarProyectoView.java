package view;

import base.View;
import controller.AgregarProyectoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Establece la vista de agregar proyecto al momento de presionarlo en el menuBar
 *
 * @author Matias Zuñiga
 */
public final class AgregarProyectoView extends View {

    private AgregarProyectoController controller;

    //MARK - Botones
    @FXML private Button cancelar;
    @FXML private Button sTrabajadores;
    @FXML private Button aceptar;
    @FXML private TextField nombreP;
    @FXML private TextField jefeP;
    @FXML private TextField montoC;
    @FXML private TextField cliente;
    @FXML private TextField telefonoC;
    @FXML private TextField mailC;
    @FXML private TextField direccion;
    @FXML private TextField ciudad;
    @FXML private TextField estado;
    @FXML private TextField pais;
    @FXML private DatePicker fechaF;
    @FXML private DatePicker fechaT;

    @Override
    public void viewDidLoad() {
        /*fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());
        montoC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    montoC.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });*/
        noNumbers();
    }

    @Override
    public void viewDidClose() {
       /* System.out.println("viewDidClose");
        nombreP.setText("");
        jefeP.setText("");
        mailC.setText("");
        montoC.setText("");
        ciudad.setText("");
        cliente.setText("");
        telefonoC.setText("");
        estado.setText("");
        pais.setText("");
        direccion.setText("");
        fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());*/
        isEmpty();
    }

    /**
     * Función que permite ingresar un proyecto a la constructora.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarAceptar(){
        if(nombreP.getText().isEmpty() || jefeP.getText().isEmpty() || montoC.getText().isEmpty()|| cliente.getText().isEmpty()|| telefonoC.getText().isEmpty() || mailC.getText().isEmpty() || direccion.getText().isEmpty() ||ciudad.getText().isEmpty()||estado.getText().isEmpty()||pais.getText().isEmpty() ||
                fechaF.getEditor().getText().isEmpty() || fechaT.getEditor().getText().isEmpty()){
                controller.showAlert("Existen casillas sin rellenar!").showAndWait();
                System.out.println("campos vacios");
        }
        else if(fechaF.getValue().isAfter(fechaT.getValue())){
            controller.showAlert("Las fechas ingresadas no coinciden.").showAndWait();
        }
        else{
            controller.presionarAceptar(nombreP,jefeP,montoC,cliente,telefonoC,mailC,direccion,ciudad,estado,pais,fechaF,fechaT);
            ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
        }
    }
    /**
     * Función que cancela el ingreso del nuevo proyecto.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarCancelar(){
        Alert alert = controller.showWarning("Esta seguro que desea cancelar?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if(result.get() == ButtonType.OK){
                ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
            }
            else{
                alert.close();
            }
        }
    }

    public void setController(AgregarProyectoController controller) {
        this.controller = controller;
    }

    public void isEmpty(){
        nombreP.setText("");
        jefeP.setText("");
        mailC.setText("");
        montoC.setText("");
        ciudad.setText("");
        cliente.setText("");
        telefonoC.setText("");
        estado.setText("");
        pais.setText("");
        direccion.setText("");
        fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());
    }

    public void noNumbers(){
        fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());
        montoC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    montoC.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
