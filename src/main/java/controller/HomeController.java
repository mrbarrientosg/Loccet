package controller;

import base.Controller;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import router.HomeRouter;
import router.MenuBarRouter;
import view.HomeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public final class HomeController extends Controller {

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

    public void agregarEspecialidad() { }

    public void listaTrabajadores() { router.listaTrabajadores(model); }

    public void agregarTrabajador() {
        router.agregarTrabajador(model);
    }

    public void avanceProyecto() { }

    public void boletaTrabajador() { }

    public void editarEspecialidad() { }

    public void eliminarEspecialidad() { }

    public void eliminarTrabajadorConstructora() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String rut;
        Trabajador t;

        mostrarListaTrabajadores();

        System.out.println("Ingrese el rut del trabajador:");
        try {
            rut = reader.readLine();
            t = model.eliminarTrabajador(rut);
            if (t != null) {
                System.out.println("Trabajador eliminado de la Constructora");
            } else {
                System.out.println("El Trabajador no existe en la Constructora");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTrabajadorProyecto() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String rut;
        Trabajador t = null;

        mostrarListaProyecto();

        String id;

        Proyecto p = null;

        System.out.println("Ingrese el id del proyecto:");
        try {
            id = reader.readLine();
            //p = model.buscarProyecto(id);
            if (p != null) {
                mostrarListaTrabajadores();
                System.out.println("Ingrese el rut del trabajador:");
                rut = reader.readLine();
                t = p.eliminarTrabajador(rut);
                if (t == null)
                    System.out.println("No existe el trabajador");
                else
                    System.out.println("Trabajador eliminado del proyecto");
            } else {
                System.out.println("No existe el Proyecto");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void equipoMaquinaria() { }

    public void facturaVentas() { }

    public void facturasCompras() { }

    public void ingresoMateriales() { }

    public void modificarProyecto() { }

    public void modificarTrabajador() {
        // TODO : Buscar al trabajador que quiere modificar
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        mostrarListaProyecto();

        String rut;
        Trabajador t;

        try {

            mostrarListaTrabajadores();

            System.out.println("Ingrese el rut del trabajador:");
            rut = reader.readLine();
            t = model.obtenerTrabajador(rut);

            if (t != null) router.modificarTrabajador(model, t);
            else System.out.println("No existe el trabajador");

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void nuevoProyecto() {
        router.nuevoProyecto();
    }

    public void eliminarProyecto() {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        mostrarListaProyecto();

        Proyecto proyecto = null;

        System.out.println("Ingrese id del proyecto:");
        try {
            proyecto = model.eliminarProyecto(lector.readLine());
            if (proyecto != null) {
                System.out.println("El proyecto se ha eliminado satisfactoriamente!");
            } else {
                System.out.println("No existe el Proyecto");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reporteIngresoGasto() { }

    public void reporteMateriales() { }

    public void reporteRRHH() {
        /*clearConsole();

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        final List<Proyecto> proyectos = model.getListaProyecto();

        Integer i = 1;

        for (Proyecto proyecto: proyectos) {
            System.out.println(i.toString() + ".- ID: " + proyecto.getId() + "\t Nombre: " + proyecto.getNombreProyecto());
            i++;
        }

        System.out.println();

        Proyecto proyecto = null;
        do {
            System.out.println("Ingrese id del proyecto:");
            try {
                proyecto = model.buscarProyecto(lector.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(proyecto == null);

        model.estimacionGasto(proyecto.getId());*/
    }

    public void inventarioMateriales() {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        mostrarListaProyecto();

        Proyecto proyecto = null;

        System.out.println("Ingrese id del proyecto:");
//        try {
//            proyecto = model.buscarProyecto(lector.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (proyecto != null)
            router.inventarioMateriales(proyecto.getInventarioMaterial(), proyecto);
        else System.out.println("No existe el Proyecto");
    }

    public void agregarHorario() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        mostrarListaProyecto();

        Proyecto proyecto = null;

        Trabajador t = null;

        try {
            System.out.println("Ingrese el id del proyecto:");
            //proyecto = model.buscarProyecto(reader.readLine());


            if (proyecto != null) {
                mostrarListaTrabajadores();
                System.out.println("Ingrese el rut del trabajador:");
                t = proyecto.obtenerTrabajador(reader.readLine());
                if (t == null)
                    System.out.println("No existe el trabajador");
                else
                    router.agregarHorario(proyecto, t);
            } else {
                System.out.println("No existe el Proyecto");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarHorario() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        mostrarListaTrabajadores();

        String rut;

        Trabajador t;

        try {

            System.out.println("Ingrese el rut del trabajador:");
            rut = reader.readLine();
            t = model.obtenerTrabajador(rut);

            if (t != null) router.mostrarHorario(t);
            else System.out.println("No existe el trabajador");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void salir() {
        router.salir();
    }

    private void mostrarListaProyecto() {
        final List<Proyecto> proyectos = model.getListaProyecto();

        Integer i = 1;

        for (Proyecto proyecto: proyectos) {
            System.out.println(i.toString() + ".- ID: " + proyecto.getId() + "\t Nombre: " + proyecto.getNombre());
            i++;
        }

        System.out.println();
    }

    private void mostrarListaTrabajadores() {
        final List<Trabajador> trabajadores = model.getConjuntoTrabajadores();

        Integer i = 1;

        for (Trabajador trabajador: trabajadores) {
            System.out.println(i.toString() + ".- RUT: " + trabajador.getRut() + "\t Nombre: " + trabajador.getNombre() + "\t Apellido:" + trabajador.getApellido());
            i++;
        }

        System.out.println();
    }
}
