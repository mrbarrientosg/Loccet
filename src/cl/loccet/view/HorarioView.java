package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.HorarioController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private TableView<?> tableHorario;

    @Override
    public void viewDidLoad() {
        nombreTrabajador.setText(controller.getNombreTrabajador());
        nombreProyecto.setText(controller.getNombreProyecto());

        horaEntrada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        minutoEntrada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        limitTimeField(horaEntrada.getEditor(), 23);
        limitTimeField(minutoEntrada.getEditor(), 59);

        horaSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        minutoSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        limitTimeField(horaSalida.getEditor(), 23);
        limitTimeField(minutoSalida.getEditor(), 59);

        tableHorario.refresh();
    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    private void agregarHorario(ActionEvent event) {
        System.out.println(((RadioButton) diasToggle.getSelectedToggle()).getText());
    }

    public void setController(HorarioController controller) {
        this.controller = controller;
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
