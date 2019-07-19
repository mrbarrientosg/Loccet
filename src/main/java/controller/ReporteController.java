package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import util.AsyncTask;
import view.ReporteView;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReporteController extends Controller {

    private ReporteView view;

    private Constructora model = Constructora.getInstance();

    public void montoContractualConstructora(Consumer<BigDecimal> consumer){
        AsyncTask.supplyAsync(() -> {
            BigDecimal montoContructoraAcumulado = new BigDecimal(0);

            Iterator<Proyecto> iterator = model.getProyectos().iterator();

            while (iterator.hasNext()) {
                montoContructoraAcumulado = montoContructoraAcumulado.add(iterator.next().getCostoEstimado());
            }

            return montoContructoraAcumulado;
        }).thenAccept(consumer);
    }

    public void hacerCostos(String idProyecto) {
        Proyecto p = model.obtenerProyecto(idProyecto);
        view.mostrarCostoProyecto(p);
    }

    public void hacerCostos() {
        view.mostrarCostoContructora(model);
    }

    public BigDecimal montoActualContructora(BigDecimal montoContractual, BigDecimal gastos) {
        return montoContractual.subtract(gastos);
    }

    public BigDecimal montoActualProyecto(BigDecimal montoContractual, BigDecimal gastos) {
        return montoContractual.subtract(gastos);
    }

    public BigDecimal montoContractualProyecto(String id){
      return model.obtenerProyecto(id).getCostoEstimado();
    }

    public String getDireccion(String id){
        return model.obtenerProyecto(id).getLocalizacion().getDireccion();
    }

    public String getPais(String id){
        return model.obtenerProyecto(id).getLocalizacion().getPais();
    }

    public String getCiudad(String id){
        return model.obtenerProyecto(id).getLocalizacion().getCiudad();
    }

    public String getCliente(String id){
        return model.obtenerProyecto(id).getNombreCliente();
    }

    public void fetchProyectos(Consumer<ObservableList<ProyectoCell>> callback) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

            model.getProyectos().forEach(proyecto -> cells.add(new ProyectoCell(proyecto)));

            return cells;
        }).thenAccept(callback);
    }

    public void setView(ReporteView view) {
        this.view = view;
    }

}

