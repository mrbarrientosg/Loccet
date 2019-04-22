package view;

import base.Injectable;
import base.View;
import controller.HorarioController;
import controller.ListaHorarioController;
import javafx.application.Platform;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Trabajador;
import router.ListaHorarioRouter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class HorarioView extends View {

    private HorarioController controller;

    @FXML
    private Label nombreTrabajador;

    @FXML
    private Label nombreProyecto;

    @FXML
    private ToggleGroup diasToggle;

    @FXML
    private Spinner<Integer> horaEntrada;

    @FXML
    private Spinner<Integer> minutoEntrada;

    @FXML
    private Spinner<Integer> horaSalida;

    @FXML
    private Spinner<Integer> minutoSalida;

    private ObjectBinding<LocalTime> entradaBinding;

    private ObjectBinding<LocalTime> salidaBinding;

    @Override
    public void viewDidLoad() {
        entradaBinding = new ObjectBinding<LocalTime>() {
            {
                super.bind(horaEntrada.valueProperty(), minutoEntrada.valueProperty());
            }

            @Override
            protected LocalTime computeValue() {
                return LocalTime.of(horaEntrada.getValue(), minutoEntrada.getValue());
            }
        };

        salidaBinding = new ObjectBinding<LocalTime>() {
            {
                super.bind(horaSalida.valueProperty(), minutoSalida.valueProperty());
            }

            @Override
            protected LocalTime computeValue() {
                return LocalTime.of(horaSalida.getValue(), minutoSalida.getValue());
            }
        };

        nombreTrabajador.setText(controller.getNombreTrabajador());
        nombreProyecto.setText(controller.getNombreProyecto());

        setupSpinner(horaEntrada,23);
        setupSpinner(minutoEntrada,59);
        limitTimeField(horaEntrada.getEditor(), 23);
        limitTimeField(minutoEntrada.getEditor(), 59);

        setupSpinner(horaSalida,23);
        setupSpinner(minutoSalida,59);
        limitTimeField(horaSalida.getEditor(), 23);
        limitTimeField(minutoSalida.getEditor(), 59);

        controller.entradaProperty().bind(entradaBinding);
        controller.salidaProperty().bind(salidaBinding);

        controller.addListView();
    }

    @Override
    public void viewDidClose() {
        getRoot().getChildren().removeIf(node -> node instanceof TableView);
        controller.setDelegate(null);
    }

    @FXML
    private void agregarHorario(ActionEvent event) {
        controller.agregarHorario(diasToggle.getToggles().indexOf(diasToggle.getSelectedToggle()) + 1);
    }

    @FXML
    private void salir(ActionEvent event) {
        ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
    }

    public void addListView(Trabajador model) {
        ListaHorarioView view = ListaHorarioRouter.create(model);
        ListaHorarioController controller = Injectable.find(ListaHorarioController.class);
        this.controller.setDelegate(controller);
        controller.setAdd(true);
        getRoot().getChildren().add(view.getRoot());
    }

    public void setController(HorarioController controller) {
        this.controller = controller;
    }

    @Override
    public VBox getRoot() {
        return (VBox) super.getRoot();
    }

    private void setupSpinner(Spinner<Integer> spinner, int max) {
        SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, 0);
        spinner.setValueFactory(factory);
        spinner.setEditable(true);
        TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
        spinner.getEditor().setTextFormatter(formatter);
        factory.valueProperty().bindBidirectional(formatter.valueProperty());
    }

    private void limitTimeField(TextField textField, int maxValue) {
        textField.textProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue.chars().allMatch(Character::isDigit)) {
                textField.setText(oldValue);
                return;
            }

            String textValue = textField.getText();

            if (textValue.length() == 0) {
                textField.setText("00");
            } else if (textValue.length() == 1) {
                textField.setText("0" + textValue);
            } else if (textValue.length() == 2) {
                if (Integer.parseInt(textValue) > maxValue) {
                    textField.setText("0" + textValue.substring(1));
                }
            } else {
                int caretPosition = textField.getCaretPosition();

                if (caretPosition <= 1) {
                    textValue = textValue.substring(0, 2);
                    if (Integer.parseInt(textValue) > maxValue) {
                        textValue = "0" + textValue.substring(1);
                    }
                    Platform.runLater(() -> textField.positionCaret(textField.getCaretPosition() + 1));
                } else {
                    textValue = textValue.substring(textValue.length() - 2, textValue.length());
                    if (Integer.parseInt(textValue) > maxValue) {
                        textValue = "0" + textValue.substring(1);
                    }
                }
                textField.setText(textValue);
            }
        });
    }
}