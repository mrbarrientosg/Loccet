package model;

import repository.memory.MemoryStoreMaterial;
import repository.StoreMaterial;

import java.math.BigDecimal;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial implements Costeable {

    // MARK: - Atributos

    private StoreMaterial storeMaterial;

    // MARK: - Constructor

    public InventarioMaterial() {
        storeMaterial = new MemoryStoreMaterial();
    };

    // MARK: - Metodos Material

    public void agregarMaterial(Material material) {
        storeMaterial.save(material);
    }

    public Material obtenerMaterial(String id){
       return storeMaterial.findById(id);
    }

    public Material eliminarMaterial(String id) {
        return storeMaterial.delete(id);
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        storeMaterial.findById(idMaterial).agregarRegistro(registroMaterial);
    }

    public Iterable<Material> obtenerMateriales() {
        return storeMaterial.findAll();
    }

    @Override
    public BigDecimal calcularCosto() {
        Iterable<Material> iterable = storeMaterial.findAll();

        BigDecimal costoTotal = new BigDecimal(0);

        iterable.forEach(material -> {
            costoTotal.add(material.getPrecio());
        });

        return costoTotal;
    }
}
