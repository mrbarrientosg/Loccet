package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
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

    public void getAll(Consumer<ObservableList<Especialidad>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            ObservableList<Especialidad> list = FXCollections.observableArrayList();

            especialidades.forEach((key, value) -> {
                list.add(value);
            });

            return list;
        }).thenAccept(callBack);
    }

}
