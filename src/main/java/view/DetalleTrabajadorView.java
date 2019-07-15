package view;

import base.View;
import controller.DetalleTrabajadorController;
import exceptions.EmptyFieldException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DetalleTrabajadorView extends View {

    private DetalleTrabajadorController controller;

    private ListaHorarioView listaHorarioView;

    @FXML
    private Button editButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField rutField;

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
    private TextField telephoneField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<?> specialityField;

    @FXML
    private VBox container;

    private Boolean isEditing;

    private BooleanProperty disable;

    @Override
    public void viewDidLoad() {
        isEditing = false;
        disable = new SimpleBooleanProperty(true);

        nameField.disableProperty().bind(disable);
        lastNameField.disableProperty().bind(disable);
        birthdayField.disableProperty().bind(disable);

        addressField.disableProperty().bind(disable);
        countryField.disableProperty().bind(disable);
        stateField.disableProperty().bind(disable);
        cityField.disableProperty().bind(disable);

        telephoneField.disableProperty().bind(disable);
        emailField.disableProperty().bind(disable);
        specialityField.disableProperty().bind(disable);

        editButton.setOnAction(this::editAction);
        exitButton.setOnAction(event -> close());
    }

    @Override
    public void viewDidShow() {
        rutField.setText(controller.getRut());

        container.getChildren().add(listaHorarioView.getRoot());
    }

    @Override
    public void viewDidClose() {
        container.getChildren().remove(listaHorarioView.getRoot());
    }

    public void bind() {
        nameField.textProperty().bindBidirectional(controller.nameProperty());
        lastNameField.textProperty().bindBidirectional(controller.lastNameProperty());

        addressField.textProperty().bindBidirectional(controller.addressProperty());
        countryField.textProperty().bindBidirectional(controller.countryProperty());
        stateField.textProperty().bindBidirectional(controller.stateProperty());
        cityField.textProperty().bindBidirectional(controller.cityProperty());

        telephoneField.textProperty().bindBidirectional(controller.telephoneProperty());
        emailField.textProperty().bindBidirectional(controller.emailProperty());
        birthdayField.valueProperty().bindBidirectional(controller.birthdayProperty());
    }

    private void editAction(ActionEvent event) {
        if (isEditing) {
            // guardar
            try {
                controller.guardar();
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

    public void setController(DetalleTrabajadorController controller) {
        this.controller = controller;
    }

    public void setListaHorarioView(ListaHorarioView listaHorarioView) {
        this.listaHorarioView = listaHorarioView;
    }
}
