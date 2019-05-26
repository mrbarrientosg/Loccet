package controller;

import base.Controller;
import model.Constructora;
import view.ReporteView;

import java.math.BigDecimal;

public class ReporteController extends Controller {
    private ReporteView view;
    private Constructora model = Constructora.getInstance();

    public BigDecimal costoProyecto(String proyecto){
      return model.obtenerProyecto(proyecto).getInventarioMaterial().costoInventario();
    }





    public void setView(ReporteView view){this.view = view;}
}
