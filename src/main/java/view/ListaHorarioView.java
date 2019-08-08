package view;

import base.Injectable;
import base.View;
import cell.HorarioCell;
import controller.ListaHorarioController;
import delegate.AddHorarioDelegate;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Horario;
import util.Dias;
import java.time.LocalTime;

/**
 * Vista que despliega los horarios
 *
 * @author Matias Barrientos
 */
public final class ListaHorarioView extends View implements AddHorarioDelegate {

    private ListaHorarioController controller;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addHorarioButton;

    @FXML
    private TableView<HorarioCell> tableHorario;

    @FXML
    private TableColumn<HorarioCell, String> diaColumn;

    @FXML
    private TableColumn<HorarioCell, String> proyectoColumn;

    @FXML
    private TableColumn<HorarioCell, LocalTime> entradaColumn;

    @FXML
    private TableColumn<HorarioCell, LocalTime> salidaColumn;

    @Override
    public void viewDidLoad() {
        controller = Injectable.find(ListaHorarioController.class);

        diaColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().dia()));
        proyectoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        entradaColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        salidaColumn.setCellValueFactory(new PropertyValueFactory<>("horaFin"));

        diaColumn.setComparator((value1, value2) -> {
            int d1 = Dias.getDay(value1);
            int d2 = Dias.getDay(value2);
            return d2 - d1;
        });

        tableHorario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                deleteButton.setDisable(false);
            else
                deleteButton.setDisable(true);
        });

        deleteButton.setOnAction(this::deleteAction);
        addHorarioButton.setOnAction(this::addHorarioAction);
    }

    @Override
    public void viewDidShow() {
        controller.fetchHorarios(tableHorario::setItems);
    }

    public void didDeleteHorario(HorarioCell cell) {
        tableHorario.getItems().remove(cell);
    }

    private void addHorarioAction(ActionEvent event) {
        Injectable.find(CrearHorarioView.class).display(controller.getRutTrabajador(), this);
    }

    private void deleteAction(ActionEvent event) {
        controller.eliminarHorario(tableHorario.getSelectionModel().getSelectedItem());
    }

    @Override
    public void didAddHorario(Horario horario) {
        tableHorario.getItems().add(new HorarioCell(horario));
    }

    public ListaHorarioView display(String rut) {
        controller.setRutTrabajador(rut);
        return this;
    }
}
