package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.HomeController;
import cl.loccet.model.Constructora;
import cl.loccet.view.HomeView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeRouter {

    public static HomeView create(Constructora model) {
        HomeView view = Injectable.find(HomeView.class);
        HomeRouter router = new HomeRouter();
        HomeController controller = new HomeController(view, model, router);

        view.setController(controller);

        return view;
    }

    public void agregarEspecialidad() {

    }

    public void agregarTrabajador() {

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
