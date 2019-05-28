package view;
import base.Injectable;
import base.View;
import cell.ProyectoCell;
import controller.ReporteController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import model.Costeable;
import router.ReporteRouter;

import java.math.BigDecimal;


public class ReporteView extends View {

    private ReporteController controller;
    private ReporteRouter router;

    private ObservableList<ProyectoCell> nombreProyectos;


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
    }
    @Override
    public void viewDidShow(){
        nombreProyectos = controller.getProyectos();
        proyectoCB.setItems(nombreProyectos);
    }

    public void mostrarCostoProyecto(Costeable c) {
        montoActualLB.setText(c.calcularCosto().toString());
    }

    public void mostrarCostoContructora(Costeable c){
        BigDecimal gastoContructora = c.calcularCosto();
        BigDecimal montoContractual = controller.montoContractualConstructora();
        CostoTotalView view = Injectable.find(CostoTotalView.class);
        view.setdatos(montoContractual.toString(), gastoContructora.toString(),
                controller.montoActual(gastoContructora,montoContractual).toString());
        view.modal().withStyle(StageStyle.TRANSPARENT).show();
    }

    @Override
    public void viewDidClose(){
        nombreProyectos.clear();
    }

    @FXML
    public void costoContructora(ActionEvent event){

    }


    @FXML
    private void cargarLabel(ActionEvent event) {

        ProyectoCell proyecto = proyectoCB.getSelectionModel().getSelectedItem();
        direccionLB.setText(controller.getDireccion(proyecto.getId()));
        clienteLB.setText(controller.getCliente(proyecto.getId()));
        paisLB.setText(controller.getPais(proyecto.getId()));
        ciudadLB.setText(controller.getCiudad(proyecto.getId()));

        //montoContractualLB.setText(proyecto.getMontoContractual().toString());
        // controller.hacerCostos(proyecto.getId());

    }


    public void setController(ReporteController controller){this.controller = controller;}
    public void setRouter(ReporteRouter router){this.router = router;}
}
