package view;

import base.View;
import controller.DetalleProyectoController;
import exceptions.EmptyFieldException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import router.DetalleProyectoRouter;
import router.ListaTrabajadorRouter;

public class DetalleProyectoView extends View {

    private DetalleProyectoController controller;

    private DetalleProyectoRouter router;

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
        isEditing = false;
        disable = new SimpleBooleanProperty(true);

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

    public void bind() {
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }

        } else {
            editButton.setText("Guardar");
            disable.setValue(false);
            isEditing = true;
        }
    }

    public void setController(DetalleProyectoController controller) {
        this.controller = controller;
    }

    public void setRouter(DetalleProyectoRouter router) {
        this.router = router;
    }

    public void setListaTrabajadorView(ListaTrabajadorView listaTrabajadorView) {
        this.listaTrabajadorView = listaTrabajadorView;
    }

    public void setInventarioMaterialView(InventarioMaterialView inventarioMaterialView) {
        this.inventarioMaterialView = inventarioMaterialView;
    }
}
