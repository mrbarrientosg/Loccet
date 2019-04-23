package view;

import base.Fragment;
import cell.HorarioCell;
import controller.ListaHorarioController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Dias;

import java.time.LocalTime;

public final class ListaHorarioView extends Fragment {

    private ListaHorarioController controller;

    @FXML
    private Label nombreTrabajador;

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

    @FXML
    private VBox bottomVbox;

    @Override
    public void viewDidLoad() {
        diaColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().dia()));
        proyectoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        entradaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        salidaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));

        setupTimeColumn(entradaColumn);
        setupTimeColumn(salidaColumn);

        diaColumn.setComparator((value1, value2) -> {
            int d1 = Dias.getDay(value1);
            int d2 = Dias.getDay(value2);
            return d2 - d1;
        });

        tableHorario.setItems(controller.getHorarioList());
    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    private void salir(ActionEvent event) {
        ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
    }

    @FXML
    private void eliminar(ActionEvent event) {
        controller.eliminarHorario(tableHorario.getSelectionModel().getSelectedItem());
    }

    public void hideComponents() {
        bottomVbox.setVisible(false);
        nombreTrabajador.setVisible(false);
    }

    public void showComponents() {
        nombreTrabajador.setText(controller.getNombreTrabajador());
        bottomVbox.setVisible(true);
        nombreTrabajador.setVisible(true);
    }

    private void setupTimeColumn(TableColumn<HorarioCell, LocalTime> timeColumn) {
        timeColumn.setCellFactory(column -> {
            TableCell<HorarioCell, LocalTime> cell = new TableCell<HorarioCell, LocalTime>() {
                @Override
                protected void updateItem(LocalTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    }
                    else {
                        setText(item.toString());
                    }
                }
            };

            return cell;
        });
    }

    public void setController(ListaHorarioController controller) {
        this.controller = controller;
    }

    public ListaHorarioController getController() {
        return controller;
    }
}
