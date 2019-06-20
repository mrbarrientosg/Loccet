package view;

import base.View;
import controller.AgregarProyectoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import router.AgregarProyectoRouter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Establece la vista de agregar proyecto al momento de presionarlo en el menuBar
 *
 * @author Matias Zuñiga
 */
public final class AgregarProyectoView extends View {

    private AgregarProyectoController controller;
    private AgregarProyectoRouter router;

    //MARK - Botones
    @FXML
    private TextField jefeP;

    @FXML
    private TextField ciudad;

    @FXML
    private TextField direccion;

    @FXML
    private TextField nombreP;

    @FXML
    private TextField montoC;

    @FXML
    private TextField cliente;

    @FXML
    private TextField mailC;

    @FXML
    private TextField pais;

    @FXML
    private TextField telefonoC;

    @FXML
    private DatePicker fechaT;

    @FXML
    private DatePicker fechaF;

    @FXML
    private TextField estado;

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @Override
    public void viewDidLoad() {
        lettersOff();
    }

    @Override
    public void viewDidClose(){
        setValues();
    }

    /**
     * Función que permite ingresar un proyecto a la constructora.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarAceptar(){
        if(nombreP.getText().isEmpty() || jefeP.getText().isEmpty() ||
                montoC.getText().isEmpty()||
                cliente.getText().isEmpty()||
                telefonoC.getText().isEmpty()||
                mailC.getText().isEmpty()||
                direccion.getText().isEmpty()||
                ciudad.getText().isEmpty()||
                estado.getText().isEmpty()||pais.getText().isEmpty() ||

                fechaF.getEditor().getText().isEmpty() || fechaT.getEditor().getText().isEmpty()){
                router.showAlert("Existen casillas sin rellenar!").showAndWait();
                System.out.println("campos vacios");
        }
        else if(fechaF.getValue().isAfter(fechaT.getValue())){
            router.showAlert("Las fechas ingresadas no coinciden.").showAndWait();
        }
        else{
            controller.presionarAceptar(nombreP.getText(),jefeP.getText(),new BigDecimal(montoC.getText()),cliente.getText(),
                    telefonoC.getText(),direccion.getText(),ciudad.getText(),estado.getText(),pais.getText()
                    ,fechaF.getValue(),fechaT.getValue());
            close();
        }
    }
    /**
     * Función que cancela el ingreso del nuevo proyecto.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarCancelar(){
        Alert alert = router.showWarning("Esta seguro que desea cancelar?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if(result.get() == ButtonType.OK){
               close();
            }
            else{
                alert.close();
            }
        }
    }



    /**
     * @author: Matías Zúñiga
     * Función que setea los valores iniciales en vacio.
     */
    public void setValues(){
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

    /**
     * @author: Matías Zúñiga
     * Función que prohibe el uso de letras en la casilla monto contractual.
     */
    public void lettersOff(){
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
    public void setController(AgregarProyectoController controller) {
        this.controller = controller;
    }
    public void setRouter(AgregarProyectoRouter router){this.router = router;}
}
