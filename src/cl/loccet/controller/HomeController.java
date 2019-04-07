package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.router.HomeRouter;
import cl.loccet.router.MenuBarRouter;
import cl.loccet.view.HomeView;
import cl.loccet.view.MenuBarView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class HomeController extends Controller {

    private final HomeView view;

    private final Constructora model;

    private final HomeRouter router;

    public HomeController(HomeView view, Constructora model, HomeRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;

        setMenuBar();
    }

    private void setMenuBar() {
        this.view.setTop(MenuBarRouter.create(this).getRoot());
    }

    // Metodos del MenuBar

    public void agregarEspecialidad() {

    }

    public void listaTrabajadores() { router.listaTrabajadores(model); }

    public void agregarTrabajador() {
        router.agregarTrabajador(model);
    }

    public void avanceProyecto() {

    }

    public void boletaTrabajador() {

    }

    public void editarEspecialidad() {

    }

    public void eliminarEspecialidad() {

    }

    public void eliminarTrabajadorConstructora() {

    }

    public void eliminarTrabajadorProyecto() {

    }

    public void equipoMaquinaria() {

    }

    public void facturaVentas() {

    }

    public void facturasCompras() {

    }

    public void ingresoMateriales() {

    }

    public void modificarProyecto() {

    }

    public void modificarTrabajador() {
        // TODO : Buscar al trabajador que quiere modificar
        router.modificarTrabajador(model, null);
    }

    public void nuevoProyecto() {

    }

    public void reporteIngresoGasto() {

    }

    public void reporteMateriales() {

    }

    public void reporteRRHH() {

    }

    public void salidaMateriales() {

    }

    public void salir() {
        router.salir();
    }
}
