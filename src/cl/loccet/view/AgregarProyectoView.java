package cl.loccet.view;
import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.AgregarProyectoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

/**
 * @author Matias Zuñiga
 * Establece la vista de agregar proyecto al momento de presionarlo en el menuBar
 */

public class AgregarProyectoView extends View {

    private AgregarProyectoController controller;

    //Botones
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

    @Override public void viewDidLoad() {
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

    @Override public void viewDidClose() {
        System.out.println("viewDidClose");
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
    }
    @FXML private void apretarAceptar(){
        if(nombreP.getText().isEmpty() || jefeP.getText().isEmpty() || montoC.getText().isEmpty()|| cliente.getText().isEmpty()|| telefonoC.getText().isEmpty() || mailC.getText().isEmpty() || direccion.getText().isEmpty() ||ciudad.getText().isEmpty()||estado.getText().isEmpty()||pais.getText().isEmpty() ||
                fechaF.getEditor().getText().isEmpty() || fechaT.getEditor().getText().isEmpty()){
                Alert alert = controller.showAlert("Existen casillas sin rellenar!");
                Optional<ButtonType> result = alert.showAndWait();
                System.out.println("campos vacios");
        }
        else if(fechaF.getValue().isAfter(fechaT.getValue())){
            Alert alert = controller.showAlert("Las fechas ingresadas no coinciden.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else{
            controller.presionarAceptar(nombreP,jefeP,montoC,cliente,telefonoC,mailC,direccion,ciudad,estado,pais,fechaF,fechaT);
            ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
            //System.out.println("nombre:" + proyecto.getNombreProyecto() + "id: " + proyecto.getId());
            //TODO: No guarda aún trabajadores

        }
    }
    @FXML private void apretarCancelar(){
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
    @FXML private void seleccionarTrabajadores(){

    }
}
