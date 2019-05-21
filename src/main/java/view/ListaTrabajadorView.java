package view;

import base.Fragment;
import base.View;
import cell.TrabajadorCell;
import controller.ListaTrabajadorController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public final class ListaTrabajadorView extends Fragment {

    private ListaTrabajadorController controller;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<TrabajadorCell> tableView;

    @FXML
    private TableColumn<TrabajadorCell, String> rutColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> nameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> lastNameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> fechaNaciemientoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> specialityColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> horasTrabajoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> sueldoHoraColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> proyectColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> telefonoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> emailColumn;

    private ObjectProperty<TrabajadorCell> selectedTrabajador = new SimpleObjectProperty<>();

    private ObservableList<TrabajadorCell> trabajadorCells;

    private FilteredList<TrabajadorCell> filteredMateriales;


    @Override
    public void viewDidLoad() {

        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        fechaNaciemientoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));
        horasTrabajoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadDeHoras"));
        sueldoHoraColumn.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHora"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));

        searchTextField.setOnKeyReleased(event -> {
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                didSearch(newValue);
            });
            refreshTable();
        });

    }

    @Override
    public void viewDidShow() {
        trabajadorCells = controller.loadData();
        filteredMateriales = new FilteredList<>(trabajadorCells, e -> true);
        selectedTrabajador.bind(tableView.getSelectionModel().selectedItemProperty());
        refreshTable();
    }

    @Override
    public void viewDidClose() {
        selectedTrabajador.unbind();
    }

    @FXML
    private void verDetalleTrabajador(ActionEvent event) {

    }

    public void setController(ListaTrabajadorController controller) {
        this.controller = controller;
    }

    public void refreshTable() {
        SortedList sortedList = new SortedList<>(filteredMateriales);
        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }

    /**
     * Filtra la busqueda de la vista
     * @param query String que contiene la busqueda de la vista
     */
    public void didSearch(String query) {
        filteredMateriales.setPredicate(materialCell ->
                materialCell.getNombre().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getRut().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getApellido().toLowerCase().contains(query.toLowerCase())
        );
    }

}
