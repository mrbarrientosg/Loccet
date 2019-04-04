package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.AgregarTrabajadorController;
import cl.loccet.model.Trabajador;
import cl.loccet.model.TrabajadorBuilder;
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
        specialityList.getItems().addAll("1", "2", "3", "4");
        specialityList.setValue("1");
        saveButton.setOnAction(this::saveHandler);
    }

    @Override
    public void viewDidClose() {

    }

    private void saveHandler(ActionEvent event) {
        TrabajadorBuilder nuevo = TrabajadorBuilder.create(nameTextField.getText(), nameTextField.getText(), lastNameTextField.getText())
                .withEspecialidad(specialityList.getValue())
                .withFechaNacimiento(birthdayDateField.getValue());


        System.out.println(controller.guardarTrabajador(nuevo.build()));

        /*controller.buscarTrabajador("Matias").forEach(trabajador -> {
            System.out.println(nuevo.getNombre());
            System.out.println(nuevo.getApellido());
        });*/

        /*LOGGER.info(nuevo.getRut());
        LOGGER.info(nuevo.getNombre());
        LOGGER.info(nuevo.getApellido());*/
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
