package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Especialidades {

    private static Especialidades instance;

    private final Map<String, Especialidad> especialidades;

    public static Especialidades getInstance() {
        if (instance == null)
            instance = new Especialidades();

        return instance;
    }

    public static void createFakeData() {
        /*Especialidades esp = getInstance();
        esp.agregar(new Especialidad("Jefe de obras", ));
        esp.agregar(new Especialidad("Obrero", 3000.0));
        esp.agregar(new Especialidad("Pintor", 2000.0));
        esp.agregar(new Especialidad("Sin asignar", 0.0));*/
    }

    private Especialidades() {
        especialidades = new HashMap<>();
    }

    public void agregar(Especialidad especialidad) {
        especialidades.put(especialidad.getNombre(), especialidad);
    }

    public Especialidad obtener(String nombre) {
        return especialidades.get(nombre);
    }

    public List<String> getAll() {
        return Collections.unmodifiableList(especialidades.values().stream()
                .map(Especialidad::getNombre)
                .collect(Collectors.toList()));
    }

}
