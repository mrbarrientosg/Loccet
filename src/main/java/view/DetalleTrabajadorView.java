package view;

import base.View;
import controller.DetalleTrabajadorController;
import exceptions.EmptyFieldException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Especialidad;
import model.Especialidades;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
    private TextField horasFields;

    @FXML
    private ComboBox<Especialidad> specialityField;

    @FXML
    private VBox partTimeVbox;

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
        horasFields.disableProperty().bind(disable);

        editButton.setOnAction(this::editAction);
        exitButton.setOnAction(event -> close());

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

        specialityField.setButtonCell(factory.call(null));
        specialityField.setCellFactory(factory);

        Especialidades.getInstance().getAll(specialityField::setItems);

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");

        TextFormatter formatter =  new TextFormatter<UnaryOperator>(change -> pattern.matcher(change.getControlNewText()).matches() ? change : null);

        horasFields.setTextFormatter(formatter);
    }

    @Override
    public void viewDidShow() {
        rutField.setText(controller.getRut());
        container.getChildren().add(listaHorarioView.getRoot());
    }

    @Override
    public void viewDidClose() {
        if (isEditing) {
            editButton.setText("Editar");
            isEditing = false;
            disable.setValue(true);
        }

        container.getChildren().remove(listaHorarioView.getRoot());
        controller.save();
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

        specialityField.valueProperty().bindBidirectional(controller.specialityProperty());

        horasFields.textProperty().bindBidirectional(controller.horasProperty());
    }

    public void hidePartTimeVbox() {
        partTimeVbox.setVisible(false);
    }

    public void showPartTimeVbox() {
        partTimeVbox.setVisible(true);
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
