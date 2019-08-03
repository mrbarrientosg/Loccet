package view;

import base.Fragment;
import base.Injectable;
import controller.DetalleMaterialController;
import exceptions.NegativeQuantityException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import util.Alert;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * Vista de agregar material.
 *
 * @author Sebastian Fuenzalida.
 */

public final class AgregarMaterialView extends Fragment {

    private DetalleMaterialController controller;

    @FXML
    private Button agregarBT;

    @FXML
    private Button cancelarBT;

    @FXML
    private TextField agregarTF;

    @Override
    public void viewDidLoad() {
        controller = Injectable.find(DetalleMaterialController.class);
    }

    @Override
    public void viewDidShow(){
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> pattern.matcher(change.getControlNewText()).matches() ? change : null);

        agregarTF.setTextFormatter(formatter);
    }

    /**
     * Evento del boton para agregar mas cantidad al material
     * @param event
     */
    @FXML
    public void cantidadItem(ActionEvent event){
        if(!agregarTF.getText().isEmpty()) {
            try {
                controller.agregarMaterial(Double.parseDouble(agregarTF.getText()));
            } catch (NegativeQuantityException e) {
                Alert.error()
                        .withDescription(e.getMessage())
                        .withButton(ButtonType.OK)
                        .build().show();
            }
            close();
        } else {
            Alert.error()
                    .withHeader("Por favor ingresar un numero")
                    .withButton(ButtonType.OK)
                    .build().show();
        }
    }

    @FXML
    public void salir(ActionEvent event) {
        close();
    }

    public void display() {
        modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

}