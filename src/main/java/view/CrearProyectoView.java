package view;

import base.Injectable;
import base.View;
import controller.CrearProyectoController;
import delegate.SaveProyectoDelegate;
import exceptions.DateRangeException;
import exceptions.EmptyFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import util.Alert;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * Vista para crear un proyecto
 *
 * @author Matias Zuñiga
 */
public final class CrearProyectoView extends View {

    private CrearProyectoController controller;

    // MARK: - Botones

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
        controller = Injectable.find(CrearProyectoController.class);

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
        } catch (EmptyFieldException | DateRangeException e) {
            Alert.error()
                    .withDescription(e.getMessage())
                    .withButton(ButtonType.OK)
                    .build().show();
        }
    }

    /**
     * Función que cancela el ingreso del nuevo proyecto.
     * @author Matías Zúñiga
     */
    @FXML
    private void apretarCancelar() {
        close();
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

    public void display(SaveProyectoDelegate delegate) {
        controller.setDelegate(delegate);
        modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }
}
