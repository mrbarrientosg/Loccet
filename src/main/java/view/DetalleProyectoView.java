package view;

import base.View;
import controller.DetalleProyectoController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DetalleProyectoView extends View {

    private DetalleProyectoController controller;

    @FXML
    private Button editButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField clientField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField startDateField;

    private Boolean isEditing;

    private BooleanProperty disable;

    @Override
    public void viewDidLoad() {
        isEditing = false;
        disable = new SimpleBooleanProperty(true);

        nameField.disableProperty().bindBidirectional(disable);
        addressField.disableProperty().bindBidirectional(disable);
        countryField.disableProperty().bindBidirectional(disable);
        stateField.disableProperty().bindBidirectional(disable);
        cityField.disableProperty().bindBidirectional(disable);
        clientField.disableProperty().bindBidirectional(disable);

        startDateField.disableProperty().bindBidirectional(disable);
        endDateField.disableProperty().bindBidirectional(disable);

        editButton.setOnAction(this::editAction);
        exitButton.setOnAction(event -> close());
    }

    public void bind() {
        nameField.textProperty().bindBidirectional(controller.nameProperty());
        addressField.textProperty().bindBidirectional(controller.addressProperty());
        countryField.textProperty().bindBidirectional(controller.countryProperty());
        stateField.textProperty().bindBidirectional(controller.stateProperty());
        cityField.textProperty().bindBidirectional(controller.cityProperty());
        clientField.textProperty().bindBidirectional(controller.clientProperty());
    }

    private void editAction(ActionEvent event) {
        if (isEditing) {
            // guardar
            controller.actualizar();
            editButton.setText("Editar");
            isEditing = false;
            disable.setValue(true);
        } else {
            editButton.setText("Guardar");
            disable.setValue(false);
            isEditing = true;
        }
    }

    public void setController(DetalleProyectoController controller) {
        this.controller = controller;
    }
}
