package view;
import base.Injectable;
import base.View;
import cell.ProyectoCell;
import controller.ReporteController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Costeable;
import router.ReporteRouter;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ReporteView extends View {

    private ReporteController controller;

    private ReporteRouter router;

    private String idProyecto;

    @FXML
    private Button costoTotalButton;

    @FXML
    private ComboBox<ProyectoCell> proyectoCB;

    @FXML
    private Button costoProyectoButton;

    @FXML
    private Label direccionLB;

    @FXML
    private Label clienteLB;

    @FXML
    private Label paisLB;

    @FXML
    private Label ciudadLB;

    @FXML
    private Label montoContractualLB;

    @FXML
    private Label gastoLB;

    @FXML
    private Label montoActualLB;

    @Override
    public void viewDidLoad() {
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

        proyectoCB.setCellFactory(factory);
        proyectoCB.setButtonCell(factory.call(null));
    }

    @Override
    public void viewDidShow(){
        controller.fetchProyectos(proyectoCells -> {
            proyectoCB.setItems(proyectoCells);
            proyectoCB.getSelectionModel().selectFirst();
        });

        proyectoCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ProyectoCell proyecto = newValue;
                idProyecto = proyecto.getId();
                direccionLB.setText(controller.getDireccion(proyecto.getId()));
                clienteLB.setText(controller.getCliente(proyecto.getId()));
                paisLB.setText(controller.getPais(proyecto.getId()));
                ciudadLB.setText(controller.getCiudad(proyecto.getId()));
            }
        });
    }

    public void mostrarCostoProyecto(Costeable c) {
        gastoLB.setText(c.calcularCosto().toString());
    }

    public void mostrarCostoContructora(Costeable c){
        BigDecimal gastoContructora = c.calcularCosto();

        controller.montoContractualConstructora(montoContractual -> {
            CostoTotalView view = Injectable.find(CostoTotalView.class);

            view.setdatos(montoContractual.toString(), gastoContructora.toString(),
                    controller.montoActualContructora(gastoContructora ,montoContractual).toString());

            view.modal().withStyle(StageStyle.TRANSPARENT)
                    .show().getScene().setFill(Color.TRANSPARENT);
        });
    }

    @FXML
    public void costoContructora(ActionEvent event){
        controller.hacerCostos();
    }

    @FXML
    private void cargarLabel(ActionEvent event) {

    }

    @FXML
    private void costoProyecto(ActionEvent event) {
        if (idProyecto != null) {
            montoContractualLB.setText(controller.montoContractualProyecto(idProyecto).toString());
            controller.hacerCostos(idProyecto);
            montoActualLB.setText(controller.montoActualProyecto(new BigDecimal(montoContractualLB.getText()),
                   new BigDecimal(gastoLB.getText())).toString());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Falta seleccionar un proyecto");
            alert.setContentText("Por favor, seleccione un proyecto");
            alert.showAndWait();
        }
    }

    public void setController(ReporteController controller) {
        this.controller = controller;
    }

    public void setRouter(ReporteRouter router) {
        this.router = router;
    }
}
