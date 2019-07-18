package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Especialidades {

    // MARK: - Atributos
    private static Especialidades instance;

    private final Map<String, Especialidad> especialidades;

    // MARK: - Constructores

    private Especialidades() {
        especialidades = new HashMap<>();
    }

    public static Especialidades getInstance() {
        if (instance == null)
            instance = new Especialidades();

        return instance;
    }

    // MARK: - Metodos Especialidad

    public void agregar(Especialidad especialidad) {
        if (especialidades.containsKey(especialidad.getNombre()))
            return;

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
