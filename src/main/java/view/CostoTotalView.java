package view;

import base.View;
import controller.ReporteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Costeable;
import model.Proyecto;
import util.AsyncTask;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.function.Consumer;

public class CostoTotalView extends View {

    @FXML
    private Label montoContractualLB;

    @FXML
    private Label gastoLB;

    @FXML
    private Label montoActualLB;

    @FXML
    private Button salirBT;


    @Override
    public void viewDidLoad() {

    }

    @FXML
    public void salir(ActionEvent event){
        close();
    }

    public void setupView(Costeable costeable, BigDecimal montoContractual) {
        AsyncTask.supplyAsync(costeable::calcularCosto).thenAccept(costo -> {
            this.gastoLB.setText(costo.toString());
            this.montoContractualLB.setText(montoContractual.toString());
            this.montoActualLB.setText(montoContractual.subtract(costo).toString());
        });
    }

}
