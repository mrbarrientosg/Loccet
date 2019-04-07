package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.TrabajadorController;
import cl.loccet.model.Especialidades;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;

public class TrabajadorView extends View {

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
    private Button saveButton;

    @Override
    public void viewDidLoad() {
        loadView();
    }

    @Override
    public void viewDidClose() {
        clearFields();
        clearBind();
    }

    public void loadView() {
        clearFields();

        birthdayDateField.setValue(LocalDate.now());
        specialityList.setItems(FXCollections.observableList(Especialidades.getInstance().getAll()));
        specialityList.getSelectionModel().selectFirst();
        saveButton.setOnAction(this::saveHandler);

        bindController();

        rutTextField.requestFocus();
    }

    private void saveHandler(ActionEvent event) {
        controller.guardarTrabajador();
    }

    @FXML
    private void cancelHandler(ActionEvent event) {
        closeView();
    }

    private void bindController() {
        controller.rutProperty().bindBidirectional(rutTextField.textProperty());
        controller.nameProperty().bindBidirectional(nameTextField.textProperty());
        controller.lastNameProperty().bindBidirectional(lastNameTextField.textProperty());
        controller.specialityProperty().bindBidirectional(specialityList.valueProperty());
        controller.birthdayProperty().bindBidirectional(birthdayDateField.valueProperty());

        controller.addressProperty().bindBidirectional(addressText.textProperty());
        controller.zipProperty().bindBidirectional(postalCodeField.textProperty());
        controller.countryProperty().bindBidirectional(countryField.textProperty());
        controller.cityProperty().bindBidirectional(cityField.textProperty());
        controller.stateProperty().bindBidirectional(stateField.textProperty());

        controller.telephoneProperty().bindBidirectional(telephoneField.textProperty());
        controller.emailProperty().bindBidirectional(emailField.textProperty());

        controller.bindEditProperty();
    }

    private void clearBind() {
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

    public void closeView() {
        ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
    }

    public void setController(TrabajadorController controller) {
        this.controller = controller;
    }

    public TrabajadorController getController() {
        return controller;
    }

}
