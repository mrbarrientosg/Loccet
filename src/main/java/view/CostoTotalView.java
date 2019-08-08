package view;

import base.Fragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Costeable;
import util.AsyncTask;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Vista para mostrar el costo total de la constructora
 *
 * @author Matias Zuñiga
 */
public final class CostoTotalView extends Fragment {

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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        df.setCurrency(Currency.getInstance(Locale.getDefault()));

        AsyncTask.supplyAsync(costeable::calcularCosto).thenAccept(costo -> {
            this.gastoLB.setText("$" + df.format(costo));
            this.montoContractualLB.setText("$" + df.format(montoContractual));
            this.montoActualLB.setText("$" + df.format(montoContractual.subtract(costo)));
        });
    }

}
