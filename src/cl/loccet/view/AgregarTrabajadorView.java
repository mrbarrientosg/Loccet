package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.AgregarTrabajadorController;
import cl.loccet.model.Especialidades;
import cl.loccet.model.Trabajador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AgregarTrabajadorView extends View {

    private HomeView master;

    private AgregarTrabajadorController controller;

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
        birthdayDateField.setValue(LocalDate.now());
        specialityList.getItems().addAll(Especialidades.getInstance().getAll());
        specialityList.getSelectionModel().selectFirst();
        saveButton.setOnAction(this::saveHandler);

        bindController();
    }

    @Override
    public void viewDidClose() {
        clearBind();
    }

    private void saveHandler(ActionEvent event) {
        controller.guardarTrabajador();
    }

    @FXML
    private void cancelHandler(ActionEvent event) {
        master.setCenter(null);
    }

    private void bindController() {
        controller.rutProperty().bind(rutTextField.textProperty());
        controller.nameProperty().bind(nameTextField.textProperty());
        controller.lastNameProperty().bind(lastNameTextField.textProperty());
        controller.specialityProperty().bind(specialityList.valueProperty());
        controller.birthdayProperty().bind(birthdayDateField.valueProperty());

        controller.addressProperty().bind(addressText.textProperty());
        controller.zipProperty().bind(postalCodeField.textProperty());
        controller.countryProperty().bind(countryField.textProperty());
        controller.cityProperty().bind(cityField.textProperty());
        controller.stateProperty().bind(stateField.textProperty());

        controller.telephoneProperty().bind(telephoneField.textProperty());
        controller.emailProperty().bind(emailField.textProperty());
    }

    private void clearBind() {
        controller.rutProperty().unbind();
        controller.nameProperty().unbind();
        controller.lastNameProperty().unbind();
        controller.specialityProperty().unbind();
        controller.birthdayProperty().unbind();

        controller.addressProperty().unbind();
        controller.zipProperty().unbind();
        controller.countryProperty().unbind();
        controller.cityProperty().unbind();
        controller.stateProperty().unbind();

        controller.telephoneProperty().unbind();
        controller.emailProperty().unbind();
    }

    public void setController(AgregarTrabajadorController controller) {
        this.controller = controller;
    }

    public void setMaster(HomeView master) {
        this.master = master;
    }

    public HomeView getMaster() {
        return master;
    }
}
