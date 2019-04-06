package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarView extends View {

    private HomeController controller;

    @Override
    public void viewDidLoad() { }

    @Override
    public void viewDidClose() { }

    @FXML
    private void agregarEspecialidad(ActionEvent event) {
        controller.agregarEspecialidad();
    }

    @FXML
    private void agregarTrabajador(ActionEvent event) {
        controller.agregarTrabajador();
    }

    @FXML
    private void avanceProyecto(ActionEvent event) {
        controller.avanceProyecto();
    }

    @FXML
    private void boletaTrabajador(ActionEvent event) {
        controller.boletaTrabajador();
    }

    @FXML
    private void editarEspecialidad(ActionEvent event) {
        controller.editarEspecialidad();
    }

    @FXML
    private void eliminarEspecialidad(ActionEvent event) {
        controller.eliminarEspecialidad();
    }

    @FXML
    private void eliminarTrabajadorConstructora(ActionEvent event) {
        controller.eliminarTrabajadorConstructora();
    }

    @FXML
    private void eliminarTrabajadorProyecto(ActionEvent event) {
        controller.eliminarTrabajadorProyecto();
    }

    @FXML
    private void equipoMaquinaria(ActionEvent event) {
        controller.equipoMaquinaria();
    }

    @FXML
    private void facturaVentas(ActionEvent event) {
        controller.facturaVentas();
    }

    @FXML
    private void facturasCompras(ActionEvent event) {
        controller.facturasCompras();
    }

    @FXML
    private void ingresoMateriales(ActionEvent event) {
        controller.ingresoMateriales();
    }

    @FXML
    private void modificarProyecto(ActionEvent event) {
        controller.modificarProyecto();
    }

    @FXML
    private void modificarTrabajador(ActionEvent event) {
        controller.modificarTrabajador();
    }

    @FXML
    private void nuevoProyecto(ActionEvent event) {
        controller.nuevoProyecto();
    }

    @FXML
    private void reporteIngresoGasto(ActionEvent event) {
        controller.reporteIngresoGasto();
    }

    @FXML
    private void reporteMateriales(ActionEvent event) {
        controller.reporteMateriales();
    }

    @FXML
    private void reporteRRHH(ActionEvent event) {
        controller.reporteRRHH();
    }

    @FXML
    private void salidaMateriales(ActionEvent event) {
        controller.salidaMateriales();
    }

    @FXML
    private void salir(ActionEvent event) {
        controller.salir();
    }

    public void setController(HomeController controller) {
        this.controller = controller;
    }
}
