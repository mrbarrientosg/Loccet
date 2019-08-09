package view;

import base.Injectable;
import base.View;
import controller.DetalleProyectoController;
import delegate.SaveProyectoDelegate;
import exceptions.EmptyFieldException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import util.Alert;

/**
 * Vista detalle de un proyecto
 *
 * @author Matias Barrientos
 */
public final class DetalleProyectoView extends View {

    private DetalleProyectoController controller;

    private ListaTrabajadorView listaTrabajadorView;

    private InventarioMaterialView inventarioMaterialView;

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
    private DatePicker endDateField;

    @FXML
    private DatePicker startDateField;

    @FXML
    private VBox container;

    private Boolean isEditing;

    private BooleanProperty disable;

    @Override
    public void viewDidLoad() {
        controller = Injectable.find(DetalleProyectoController.class);
        isEditing = false;
        disable = new SimpleBooleanProperty(true);
        bind();

        nameField.disableProperty().bind(disable);
        addressField.disableProperty().bind(disable);
        countryField.disableProperty().bind(disable);
        stateField.disableProperty().bind(disable);
        cityField.disableProperty().bind(disable);
        clientField.disableProperty().bind(disable);

        //startDateField.disableProperty().bindBidirectional(disable);
        //endDateField.disableProperty().bindBidirectional(disable);

        editButton.setOnAction(this::editAction);
        exitButton.setOnAction(event -> close());
    }

    @Override
    public void viewDidShow() {
        idField.setText(controller.getIdProyecto());

        container.getChildren().add(listaTrabajadorView.getRoot());
        container.getChildren().add(inventarioMaterialView.getRoot());
    }

    @Override
    public void viewDidClose() {
        if (isEditing) {
            editButton.setText("Editar");
            isEditing = false;
            disable.setValue(true);
        }

        container.getChildren().remove(listaTrabajadorView.getRoot());
        container.getChildren().remove(inventarioMaterialView.getRoot());

        controller.save();
    }

    private void bind() {
        nameField.textProperty().bindBidirectional(controller.nameProperty());
        addressField.textProperty().bindBidirectional(controller.addressProperty());
        countryField.textProperty().bindBidirectional(controller.countryProperty());
        stateField.textProperty().bindBidirectional(controller.stateProperty());
        cityField.textProperty().bindBidirectional(controller.cityProperty());
        clientField.textProperty().bindBidirectional(controller.clientProperty());
        startDateField.valueProperty().bind(controller.startDateProperty());
        endDateField.valueProperty().bind(controller.endDateProperty());
    }

    private void editAction(ActionEvent event) {
        if (isEditing) {
            // guardar
            try {
                controller.actualizar();
                editButton.setText("Editar");
                isEditing = false;
                disable.setValue(true);
            } catch (EmptyFieldException e) {
                Alert.error()
                        .withDescription(e.getMessage())
                        .withButton(ButtonType.OK)
                        .build().show();
            }

        } else {
            editButton.setText("Guardar");
            disable.setValue(false);
            isEditing = true;
        }
    }

    public void display(String id, SaveProyectoDelegate delegate) {
        controller.setIdProyecto(id);
        controller.setDelegate(delegate);

        listaTrabajadorView = Injectable.find(ListaTrabajadorView.class).display(id);

        inventarioMaterialView = Injectable.find(InventarioMaterialView.class).display(id);

        modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }
}
