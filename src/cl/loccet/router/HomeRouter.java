package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.HomeController;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.view.ListaTrabajadorView;
import cl.loccet.view.TrabajadorView;
import cl.loccet.view.HomeView;
import javafx.application.Platform;

public class HomeRouter {

    private static HomeView master;

    public static HomeView create(Constructora model) {
        HomeView view = Injectable.find(HomeView.class);
        HomeRouter router = new HomeRouter();
        HomeController controller = new HomeController(view, model, router);

        view.setController(controller);

        master = view;

        return view;
    }

    public void agregarEspecialidad() {

    }

    public void listaTrabajadores(Constructora model) {
        ListaTrabajadorView view = ListaTrabajadorRouter.create(model);

        view.setMaster(master);

        master.setCenter(view.getRoot());
    }

    public void agregarTrabajador(Constructora model) {
        TrabajadorView trabajadorView = TrabajadorRouter.create(model);


        master.setCenter(trabajadorView.getRoot());
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

    public void modificarTrabajador(Constructora model, Trabajador old) {
        TrabajadorView trabajadorView = TrabajadorRouter.create(model, old);


        master.setCenter(trabajadorView.getRoot());
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
        Platform.exit();
        System.exit(0);
    }
}
