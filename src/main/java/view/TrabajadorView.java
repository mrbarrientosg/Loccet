package view;

import base.View;
import controller.TrabajadorController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
    private ComboBox<String> specialityList;

    @FXML
    private DatePicker birthdayDateField;

    @FXML
    private TextArea addressText;

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
    private Button aceptar;

    @FXML
    private Button cancelar;

    @Override
    public void viewDidLoad() {
        loadView();
    }

    @Override
    public void viewDidClose() {
        clearFields();
    }

    public void loadView() {
        clearFields();

        birthdayDateField.setValue(LocalDate.now());
        specialityList.setItems(FXCollections.observableList(Especialidades.getInstance().getAll()));
        specialityList.getSelectionModel().selectFirst();
        aceptar.setOnAction(this::saveHandler);

        bindController();

        rutTextField.requestFocus();
    }

    @FXML
    private void saveHandler(ActionEvent event) {
        //controller.guardarTrabajador();
    }

    @FXML
    private void cancelHandler(ActionEvent event) {
        close();
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
    }

    /*private void clearBind() {
        controller.rutProperty().unbindBidirectional(rutTextField.textProperty());
        controller.nameProperty().unbindBidirectional(nameTextField.textProperty());
        controller.lastNameProperty().unbindBidirectional(lastNameTextField.textProperty());
        controller.specialityProperty().unbindBidirectional(specialityList.valueProperty());
        controller.birthdayProperty().unbindBidirectional(birthdayDateField.valueProperty());

        controller.addressProperty().unbindBidirectional(addressText.textProperty());
        controller.zipProperty().unbindBidirectional(postalCodeField.textProperty());
        controller.countryProperty().unbindBidirectional(countryField.textProperty());
        controller.cityProperty().unbindBidirectional(cityField.textProperty());
        controller.stateProperty().unbindBidirectional(stateField.textProperty());

        controller.telephoneProperty().unbindBidirectional(telephoneField.textProperty());
        controller.emailProperty().unbindBidirectional(emailField.textProperty());
    }*/

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
