package view;

import base.View;
import controller.AgregarProyectoController;
import exceptions.EmptyFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import router.AgregarProyectoRouter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
    private TextField pais;

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
    public void viewDidShow() {
        bindController();
    }

    @Override
    public void viewDidClose() {
        clearValues();
    }

    /**
     * Función que permite ingresar un proyecto a la constructora.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarAceptar() {
        try {
            controller.saveProyecto();
            close();
        } catch (EmptyFieldException e) {
            Alert alert = router.showWarning(e.getMessage());
            alert.show();
        }
    }
    /**
     * Función que cancela el ingreso del nuevo proyecto.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarCancelar() {
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

    private void bindController() {
        nombreP.textProperty().bindBidirectional(controller.nameProperty());
        cliente.textProperty().bindBidirectional(controller.clienteProperty());
        montoC.textProperty().bindBidirectional(controller.montoProperty());

        direccion.textProperty().bindBidirectional(controller.addressProperty());
        pais.textProperty().bindBidirectional(controller.countryProperty());
        ciudad.textProperty().bindBidirectional(controller.cityProperty());
        estado.textProperty().bindBidirectional(controller.stateProperty());

        fechaF.valueProperty().bindBidirectional(controller.fechaInicioProperty());
        fechaT.valueProperty().bindBidirectional(controller.fechaTerminoProperty());
    }


    /**
     * Función que setea los valores iniciales en vacio.
     * @author Matías Zúñiga
     */
    public void clearValues(){
        nombreP.setText("");
        montoC.setText("");
        ciudad.setText("");
        cliente.setText("");
        estado.setText("");
        pais.setText("");
        direccion.setText("");
        fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());
    }

    /**
     * Función que prohibe el uso de letras en la casilla monto contractual.
     * @author Matías Zúñiga
     */
    public void lettersOff(){
        fechaF.setValue(LocalDate.now());
        fechaT.setValue(LocalDate.now());

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null);

        montoC.setTextFormatter(formatter);
    }

    public void setController(AgregarProyectoController controller) {
        this.controller = controller;
    }

    public void setRouter(AgregarProyectoRouter router) {
        this.router = router;
    }
}
