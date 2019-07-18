package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Especialidades {

    // MARK: - Atributos
    private static Especialidades instance;

    private final Map<Integer, Especialidad> especialidades;

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
        if (especialidades.containsKey(especialidad.getId()))
            return;

        especialidades.put(especialidad.getId(), especialidad);
    }

    public Especialidad obtener(Integer id) {
        return especialidades.get(id);
    }

    public List<String> getAll() {
        return Collections.unmodifiableList(especialidades.values().stream()
                .map(Especialidad::getNombre)
                .collect(Collectors.toList()));
    }

}
