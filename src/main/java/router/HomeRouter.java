package router;

import base.Injectable;
import controller.HomeController;
import javafx.application.Platform;
import model.Constructora;
import model.InventarioMaterial;
import model.Proyecto;
import model.Trabajador;
import util.NodeUtils;
import view.*;

public final class HomeRouter {

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

        NodeUtils.replaceWith(master.getRoot().getCenter(), view.getRoot(), true, true, null);

        view.setMaster(master);
    }

    public void agregarTrabajador(Constructora model) {
        TrabajadorView trabajadorView = TrabajadorRouter.create(model);

        NodeUtils.replaceWith(master.getRoot().getCenter(), trabajadorView.getRoot(), true, true, null);

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

        NodeUtils.replaceWith(master.getRoot().getCenter(), trabajadorView.getRoot(), true, true, null);

    }

    public void nuevoProyecto(Constructora model) {
        AgregarProyectoView view = AgregarProyectoRouter.create(model);

        NodeUtils.replaceWith(master.getRoot().getCenter(), view.getRoot(), true, true, null);

    }

    public void reporteIngresoGasto() {

    }

    public void reporteMateriales() {

    }

    public void reporteRRHH() {

    }

    public void inventarioMateriales(InventarioMaterial model, Proyecto proyecto) {
        InventarioMaterialView inventarioMaterialView = InventarioMaterialRouter.create(model, proyecto);

        NodeUtils.replaceWith(master.getRoot().getCenter(), inventarioMaterialView.getRoot(), true, true, null);
    }

    public void agregarHorario(Proyecto proyecto, Trabajador trabajador) {
        HorarioView horarioView = HorarioRouter.create(proyecto, trabajador);

        NodeUtils.replaceWith(master.getRoot().getCenter(), horarioView.getRoot(), true, true, null);

    }

    public void mostrarHorario(Trabajador trabajador) {
        ListaHorarioView view = ListaHorarioRouter.create(trabajador);

        NodeUtils.replaceWith(master.getRoot().getCenter(), view.getRoot(), true, true, null);
    }

        public void salir() {
        Platform.exit();
        System.exit(0);
    }
}
