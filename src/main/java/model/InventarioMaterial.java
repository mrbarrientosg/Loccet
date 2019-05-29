package model;

import repository.memory.MemoryRepositoryMaterial;
import repository.RepositoryMaterial;

import java.math.BigDecimal;
import java.util.*;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial implements Costeable {

    // MARK: - Atributos

    private RepositoryMaterial repositoryMaterial;

    // MARK: - Constructor

    public InventarioMaterial() {
        repositoryMaterial = new MemoryRepositoryMaterial();
    };

    // MARK: - Metodos Material

    public void agregarMaterial(Material material) {
        repositoryMaterial.add(material);
    }

    public Material obtenerMaterial(String id){
       return repositoryMaterial.get(id);
    }

    public Material actualizarMaterial(Material material) {
        return repositoryMaterial.update(material);
    }

    public Material eliminarMaterial(String id) {
        return repositoryMaterial.remove(repositoryMaterial.get(id));
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        repositoryMaterial.get(idMaterial).agregarRegistro(registroMaterial);
    }

    public Iterable<Material> obtenerMateriales() {
        return repositoryMaterial.get();
    }

    @Override
    public BigDecimal calcularCosto() {
        Iterable<Material> iterable = repositoryMaterial.get();

        BigDecimal costoTotal = new BigDecimal(0);

        iterable.forEach(material -> {
            costoTotal.add(material.getPrecio());
        });

        return costoTotal;
    }
}
