package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.AgregarTrabajadorController;
import cl.loccet.model.Especialidades;
import cl.loccet.model.Trabajador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private Button saveButton;

    @Override
    public void viewDidLoad() {
        birthdayDateField.setValue(LocalDate.now());
        specialityList.getItems().addAll(Especialidades.getInstance().getAll());
        specialityList.getSelectionModel().selectFirst();
        saveButton.setOnAction(this::saveHandler);
    }

    @Override
    public void viewDidClose() {

    }

    private void saveHandler(ActionEvent event) {
        Trabajador nuevo = new Trabajador.Builder()
                .rut(rutTextField.getText())
                .nombre(nameTextField.getText())
                .apellido(lastNameTextField.getText())
                .especialidad(Especialidades.getInstance().get(specialityList.getValue()))
                .fechaNaciemiento(birthdayDateField.getValue())
                .build();

        controller.guardarTrabajador(nuevo);
    }

    @FXML
    private void cancelHandler(ActionEvent event) {
        master.setCenter(null);
    }

    public void setController(AgregarTrabajadorController controller) {
        this.controller = controller;
    }

    public void setMaster(HomeView master) {
        this.master = master;
    }
}
