package controller;

import base.Controller;
import cell.ProyectoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import view.ReporteView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteController extends Controller {



    private ReporteView view;
    private Constructora model = Constructora.getInstance();

    public BigDecimal montoContractualConstructora(){
        List<Proyecto> proyectos = model.getListaProyecto();
        BigDecimal montoContructoraAcumulado = new BigDecimal(0);
        for (int i  = 0 ; i < proyectos.size(); i++){
            montoContructoraAcumulado.add(proyectos.get(i).getCostoEstimado());
        }
        return montoContructoraAcumulado;
    }

    public void hacerCostos(String idProyecto) {
        Proyecto p = model.obtenerProyecto(idProyecto);
        view.mostrarCostoProyecto(p);
    }
    public void hacerCostos(){

        view.mostrarCostoContructora(model);
    }
    public BigDecimal montoActualContructora(BigDecimal montoContractual, BigDecimal gastos){
        return montoContractual.subtract(gastos);
    }
    public BigDecimal montoActualProyecto(BigDecimal montoContractual, BigDecimal gastos){
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

    public ObservableList<ProyectoCell> getProyectos() {
        return FXCollections.observableList(model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList()));
    }

    public void setView(ReporteView view){this.view = view;}


}

