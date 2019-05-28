package view;

import base.View;
import controller.ReporteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigDecimal;

public class CostoTotalView extends View {
     private String montoContractualConstructora;
     private String gastoContructora;
     private String montoActual;

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

    @Override
    public void viewDidShow(){
        cargarLabel();
    }



    private void cargarLabel(){
        montoContractualLB.setText(montoContractualConstructora);
        gastoLB.setText(gastoContructora);
        montoActualLB.setText(montoActual);
    }

   public void salir(ActionEvent event){
       close();
   }



    public void setdatos(String montoContractualConstructora,String gastoContructora, String montoActual){
        this.montoContractualConstructora = montoContractualConstructora;
        this.montoActual = montoActual;
        this.gastoContructora = gastoContructora;

    }
}
