package view;

import base.Injectable;
import base.View;
import controller.CrearTrabajadorController;
import delegate.SaveTrabajadorDelegate;
import exceptions.EmptyFieldException;
import exceptions.InvalidRutException;
import exceptions.ObjectExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Especialidad;
import model.Especialidades;
import util.Alert;

import java.time.LocalDate;

/**
 * Vista para poder crear un nuevo trabajador
 *
 * @author Matias Barrientos
 */
public final class CrearTrabajadorView extends View {

    private CrearTrabajadorController controller;

    @FXML
    private TextField rutTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ComboBox<Especialidad> specialityList;

    @FXML
    private DatePicker birthdayDateField;

    @FXML
    private TextField addressText;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private RadioButton partTimeButton;

    @FXML
    private TextField hoursTextField;

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @Override
    public void viewDidLoad() {
        controller = Injectable.find(CrearTrabajadorController.class);

        bindController();

        aceptar.setOnAction(this::saveHandler);

        cancelar.setOnAction(event -> close());

        rutTextField.requestFocus();

        partTimeButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            hoursTextField.setDisable(!newValue);
        });

        Callback<ListView<Especialidad>, ListCell<Especialidad>> factory = lv -> new ListCell<Especialidad>() {
            @Override
            protected void updateItem(Especialidad item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        };

        specialityList.setCellFactory(factory);
        specialityList.setButtonCell(factory.call(null));
    }

    @Override
    public void viewDidShow() {
        birthdayDateField.setValue(LocalDate.now());

        Especialidades.getInstance().getAll(especialidads -> {
            specialityList.setItems(especialidads);
            specialityList.getSelectionModel().selectFirst();
        });
    }

    @Override
    public void viewDidClose() {
        clearFields();
    }

    private void saveHandler(ActionEvent event) {
        try {
            controller.guardarTrabajador();
            close();
        } catch (EmptyFieldException | InvalidRutException | ObjectExistException e) {
            Alert.error()
                    .withDescription(e.getMessage())
                    .withButton(ButtonType.OK)
                    .build().show();
        }
    }

    private void bindController() {
        rutTextField.textProperty().bindBidirectional(controller.rutProperty());
        nameTextField.textProperty().bindBidirectional(controller.nameProperty());
        lastNameTextField.textProperty().bindBidirectional(controller.lastNameProperty());
        specialityList.valueProperty().bindBidirectional(controller.specialityProperty());
        birthdayDateField.valueProperty().bindBidirectional(controller.birthdayProperty());

        addressText.textProperty().bindBidirectional(controller.addressProperty());
        postalCodeField.textProperty().bindBidirectional(controller.zipProperty());
        countryField.textProperty().bindBidirectional(controller.countryProperty());
        cityField.textProperty().bindBidirectional(controller.cityProperty());
        stateField.textProperty().bindBidirectional(controller.stateProperty());

        telephoneField.textProperty().bindBidirectional(controller.telephoneProperty());
        emailField.textProperty().bindBidirectional(controller.emailProperty());

        partTimeButton.selectedProperty().bindBidirectional(controller.partTimeProperty());
        hoursTextField.textProperty().bindBidirectional(controller.hoursProperty());
    }

    private void clearFields() {
        rutTextField.setText("");
        nameTextField.setText("");
        lastNameTextField.setText("");

        addressText.setText("");
        postalCodeField.setText("");
        countryField.setText("");
        cityField.setText("");
        stateField.setText("");

        telephoneField.setText("");
        emailField.setText("");
    }

    public void display(SaveTrabajadorDelegate delegate) {
        controller.setDelegate(delegate);
        modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

}
