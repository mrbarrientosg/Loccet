package view;

import base.Injectable;
import base.View;
import controller.DetalleMaterialController;
import exceptions.NegativeQuantityException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import util.Alert;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * Vista de Retirar material.
 *
 * @author Sebastian Fuenzalida.
 */
public final class RetirarMaterialView extends View {

    private DetalleMaterialController controller;

    @FXML
    private Button retirarBT;

    @FXML
    private Button cancelarBT;

    @FXML
    private TextField retirarTF;

    @Override
    public void viewDidLoad() {
        controller = Injectable.find(DetalleMaterialController.class);

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null);

        retirarTF.setTextFormatter(formatter);
    }

    @Override
    public void viewDidClose() {
        retirarTF.setText("");
    }

    @FXML
    public void cantidadItem(ActionEvent event){
       if(!retirarTF.getText().isEmpty()) {
           String lector = retirarTF.getText();
           double aux = Double.parseDouble(lector);
           try {
               controller.retirarMaterial(aux);
               close();
           } catch (NegativeQuantityException e) {
               Alert.error()
                       .withDescription(e.getMessage())
                       .withButton(ButtonType.OK)
                       .build().show();
           }
       } else {
           Alert.error()
                   .withDescription("Por favor ingresar un numero")
                   .withButton(ButtonType.OK)
                   .build().show();
       }
    }

    @FXML
    public void cancelar(ActionEvent event){
        close();
    }

}
