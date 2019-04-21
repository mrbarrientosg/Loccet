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
        BufferedReader reader;
        String rut;
        Trabajador t;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el rut del trabajador:");
                rut = reader.readLine();
                t = model.eliminarTrabajador(rut);
            } while (t == null);

            System.out.println("Trabajador eliminado de la Constructora");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarTrabajadorProyecto() {
        BufferedReader reader;
        String rut;
        Trabajador t = null;

        final List<Proyecto> proyectos = model.getListaProyecto();

        Integer i = 1;

        for (Proyecto proyecto: proyectos) {
            System.out.println(i.toString() + ".- ID: " + proyecto.getId() + "\t Nombre: " + proyecto.getNombreProyecto());
            i++;
        }

        System.out.println();

        String id;

        Proyecto p = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el id del proyecto:");
                id = reader.readLine();
                p = model.buscarProyecto(id);


                if (p != null) {
                    System.out.println("Ingrese el rut del trabajador:");
                    rut = reader.readLine();
                    t = p.eliminarTrabajador(rut);
                    if (t == null)
                        System.out.println("No existe el trabajador");
                    else
                        System.out.println("Trabajador eliminado del proyecto");
                }

            } while (p == null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void equipoMaquinaria() { }

    public void facturaVentas() { }

    public void facturasCompras() { }

    public void ingresoMateriales() { }

    public void modificarProyecto() { }

    public void modificarTrabajador() {
        // TODO : Buscar al trabajador que quiere modificar
        Trabajador t = model.buscarTrabajador("19").get(0);
        router.modificarTrabajador(model, t);
    }

    public void nuevoProyecto() {
        router.nuevoProyecto(model);
    }

    public void eliminarProyecto() {
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
                proyecto = model.eliminarProyecto(lector.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(proyecto == null);
        System.out.println("El proyecto se ha eliminado satisfactoriamente!");
    }

    public void reporteIngresoGasto() { }

    public void reporteMateriales() { }

    public void reporteRRHH() {
        clearConsole();

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

        model.estimacionGasto(proyecto.getId());
    }

    public void inventarioMateriales() {
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

        router.inventarioMateriales(proyecto.getInventarioMaterial());
    }

    public void agregarHorario() {
        BufferedReader reader;
        String rut;
        Trabajador t = null;

        for (Proyecto p: model.getListaProyecto()) {
            System.out.println(String.valueOf(p.getId()) + "\t" + p.getNombreProyecto());
        }

        String id;

        Proyecto p = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el id del proyecto:");
                id = reader.readLine();
                p = model.buscarProyecto(id);


                if (p != null) {
                    System.out.println("Ingrese el rut del trabajador:");
                    rut = reader.readLine();
                    t = p.obtenerTrabajador(rut);
                    if (t == null)
                        System.out.println("No existe el trabajador");
                    else
                        router.agregarHorario(p, t);
                }

            } while (p == null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarHorario() {
        BufferedReader reader;
        String rut;
        Trabajador t;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("Ingrese el rut del trabajador:");
                rut = reader.readLine();
                t = model.obtenerTrabajador(rut);
            } while (t == null);

            router.mostrarHorario(t);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void salir() {
        router.salir();
    }

    private void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
