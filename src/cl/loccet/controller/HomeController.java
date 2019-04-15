package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Proyecto;
import cl.loccet.model.Trabajador;
import cl.loccet.router.HomeRouter;
import cl.loccet.router.MenuBarRouter;
import cl.loccet.view.HomeView;
import cl.loccet.view.MenuBarView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        BufferedReader reader;
        String rut;
        Trabajador t;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el rut del trabajador:");
                rut = reader.readLine();
                t = model.eliminarTrabajador(rut);
                System.out.println(t);
            } while (t == null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarTrabajadorProyecto() {
        BufferedReader reader;
        String rut;
        Trabajador t = null;

        for (Proyecto p: model.getListaProyecto()) {
            System.out.println(String.valueOf(p.getId()) + "/t" + p.getNombreProyecto());
        }

        String id;

        Proyecto p = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el id del proyecto:");
                id = reader.readLine();
                p = model.buscarProyecto(Integer.parseInt(id));


                if (p != null) {
                    System.out.println("Ingrese el rut del trabajador:");
                    rut = reader.readLine();
                    t = p.eliminarTrabajador(rut);
                }

            } while (t != null || p != null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        Trabajador t = model.buscarTrabajador("19").get(0);
        router.modificarTrabajador(model, t);
    }

    public void nuevoProyecto() {

    }
    public void eliminarProyecto() throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Proyecto proyecto;
        do{
            System.out.println("Ingrese id del proyecto");
            int aux = Integer.parseInt(lector.readLine());
            proyecto = model.eliminarProyecto(aux);
        }while(proyecto == null);
        System.out.println("El proyecto se ha eliminado satisfactoriamente!");
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
