package view;

import base.Injectable;
import base.View;
import cell.ProyectoCell;
import controller.CrearHorarioController;
import delegate.AddHorarioDelegate;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import java.time.LocalTime;

public final class CrearHorarioView extends View {

    private CrearHorarioController controller;

    @FXML
    private ComboBox<ProyectoCell> proyectList;

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
        controller = Injectable.find(CrearHorarioController.class);
        controller.setView(this);

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

        Callback<ListView<ProyectoCell>, ListCell<ProyectoCell>> factory = lv -> new ListCell<ProyectoCell>() {
            @Override
            protected void updateItem(ProyectoCell item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        };

        proyectList.setCellFactory(factory);
        proyectList.setButtonCell(factory.call(null));
    }

    @Override
    public void viewDidShow() {
        loadView();
        controller.fetchProyectos(proyectList::setItems);
    }

    @Override
    public void viewDidClose() {
        controller.setDelegate(null);
    }

    @FXML
    private void agregarHorario(ActionEvent event) {
        if (proyectList.getValue() == null)
            // TODO: Falta morstrar alerta de que debe seleccionar un proyecto
            return;

        controller.agregarHorario(diasToggle.getToggles().indexOf(diasToggle.getSelectedToggle()) + 1, proyectList.getValue());
    }

    @FXML
    private void salir(ActionEvent event) {
        close();
    }

    private void loadView() {
        refreshView();

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
    }

    public void refreshView() {
        horaEntrada.getEditor().setText("00");
        minutoEntrada.getEditor().setText("00");

        horaSalida.getEditor().setText("00");
        minutoSalida.getEditor().setText("00");
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

    public void display(String rut, AddHorarioDelegate delegate) {
        controller.setRutTrabajador(rut);
        controller.setDelegate(delegate);
        modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

}
