package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Costeable;
import model.Proyecto;
import util.AsyncTask;
import view.ReporteView;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Controlador de la vista ReporteView
 *
 * @see view.ReporteView
 *
 * @author Matias Zuñiga
 * @author Sebastian Fuenzalida
 */
public final class ReporteController extends Controller {

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

    public Costeable costos(String idProyecto) {
        return model.obtenerProyecto(idProyecto);
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

