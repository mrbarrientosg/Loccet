package util;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que crea la informacion falsa
 */
public final class FakeData {

    public static Constructora createFakeData() {
        Especialidades.createFakeData();
        Constructora c = Constructora.getInstance();
        createFakeProyectos().forEach(c::agregarProyecto);
        List<Trabajador> list = createFakeTrabajadores();
        c.getListaProyecto().forEach(proyecto -> {

            list.forEach(t -> {
                c.agregarTrabajador(proyecto.getId(), t);
            });
        });
        return c;
    }


    private static List<Proyecto> createFakeProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();

        Proyecto p = new Proyecto.Builder("Construción Casa", "Matias", 40000000.0, "Juanito")
                .fechaProyecto(LocalDate.of(2018, 7, 13), LocalDate.now())
                .build();

        createFakeMateriales().forEach(p.getInventarioMaterial()::nuevoItem);

        proyectos.add(p);

        p = new Proyecto.Builder("Edificio la colmena", "Javier", 4000000000.0, "Jaime")
                .fechaProyecto(LocalDate.of(2015, 7, 13), LocalDate.now())
                .build();

        createFakeMateriales().forEach(p.getInventarioMaterial()::nuevoItem);

        proyectos.add(p);

        p = new Proyecto.Builder("Laguna el loro", "Lorena", 235000000.0, "Sebastian")
                .fechaProyecto(LocalDate.of(2010, 7, 13), LocalDate.now())
                .build();

        createFakeMateriales().forEach(p.getInventarioMaterial()::nuevoItem);

        proyectos.add(p);

        p = new Proyecto.Builder("Mall el quisco", "Alen", 20000000.0, "Marco")
                .fechaProyecto(LocalDate.of(2014, 7, 13), LocalDate.now())
                .build();

        createFakeMateriales().forEach(p.getInventarioMaterial()::nuevoItem);

        proyectos.add(p);

        return proyectos;
    }


    private static List<Trabajador> createFakeTrabajadores() {
        List<Trabajador> trabajadors = new ArrayList<>();

        trabajadors.add(new Trabajador.Builder()
                .rut("14.234.123-0")
                .nombre("Matias")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Jefe de obras"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("13.234.254-0")
                .nombre("Javier")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Jefe de obras"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("12.234.223-0")
                .nombre("Lorena")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Jefe de obras"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("11.234.271-0")
                .nombre("Alen")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Jefe de obras"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("14.344.280-0")
                .nombre("Felipe")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Pintor"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("13.235.280-0")
                .nombre("Bastian")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Pintor"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("12.212.280-0")
                .nombre("Marcelo")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Obrero"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        trabajadors.add(new Trabajador.Builder()
                .rut("17.234.280-0")
                .nombre("Jose")
                .apellido("")
                .especialidad(Especialidades.getInstance().obtener("Obrero"))
                .fechaNacimiento(LocalDate.now())
                .localizacion(new Localizacion.Builder().build())
                .build());

        return trabajadors;
    }

    private static List<Material> createFakeMateriales() {
        List<Material> materiales = new ArrayList<>();


        materiales.add(new Material("Clavo", "Clavo 3 cm", 1000.0, "UN", 10.0));
        materiales.add(new Material("Tornillo", "Tornillo 20 cm", 1425.0, "UN", 20.0));
        materiales.add(new Material("Plancha de Madera", "No plancha", 1000.0, "M2", 100000.0));
        materiales.add(new Material("Pintura", null, 100.0, "L", 10000.0));

        return materiales;
    }
}