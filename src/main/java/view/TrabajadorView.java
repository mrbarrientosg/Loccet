package view;

import base.View;
import cell.ProyectoCell;
import controller.TrabajadorController;
import exceptions.EmptyFieldException;
import exceptions.InvalidaRutException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import model.Especialidad;
import model.Especialidades;

import java.time.LocalDate;

public final class TrabajadorView extends View {

    private TrabajadorController controller;

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

        Especialidades.getInstance().getAll(specialityList::setItems);
        specialityList.getSelectionModel().selectFirst();

        bindController();
    }

    @Override
    public void viewDidClose() {
        clearFields();
    }

    private void saveHandler(ActionEvent event) {
        try {
            controller.guardarTrabajador();
            close();
        } catch (EmptyFieldException | InvalidaRutException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
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

    public void setController(TrabajadorController controller) {
        this.controller = controller;
    }

}
