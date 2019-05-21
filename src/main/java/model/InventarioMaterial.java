package model;

import repository.memory.MemoryRepositoryMaterial;
import repository.RepositoryMaterial;

import java.util.*;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial {

    private String id;

    private RepositoryMaterial repositoryMaterial;

    public InventarioMaterial() {
        repositoryMaterial = new MemoryRepositoryMaterial();
    };

    public List<Material> obtenerMateriales() {
        List<Material> list = new ArrayList<>();
        repositoryMaterial.get().forEachRemaining(list::add);
        return list;
    }

    public void agregarMaterial(Material material) {
        repositoryMaterial.add(material);
    }

    public Material actualizarMaterial(Material material) {
        return repositoryMaterial.update(material);
    }

    public Material eliminarMaterial(String id) {
        return repositoryMaterial.remove(repositoryMaterial.get(id));
    }





    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        repositoryMaterial.get(idMaterial).agregarRegistro(registroMaterial);
    }
}
