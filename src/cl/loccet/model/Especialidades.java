package cl.loccet.model;

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
        Especialidades esp = getInstance();
        esp.put(new Especialidad("Jefe de obras", 0.0, 0.0));
        esp.put(new Especialidad("Obrero", 0.0, 0.0));
        esp.put(new Especialidad("Pintor", 0.0, 0.0));
        esp.put(new Especialidad("Sin asignar", 0.0, 0.0));
    }

    private Especialidades() {
        especialidades = new HashMap<>();
    }

    public void put(Especialidad especialidad) {
        especialidades.put(especialidad.getNombre(), especialidad);
    }

    public Especialidad get(String nombre) {
        return especialidades.get(nombre);
    }

    public List<String> getAll() {
        return Collections.unmodifiableList(especialidades.values().stream()
                .map(Especialidad::getNombre)
                .collect(Collectors.toList()));
    }

}
