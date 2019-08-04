package model;

import model.store.MemorySpecification;
import model.store.memory.MemoryStoreMaterial;
import model.store.StoreMaterial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial implements Costeable, Cleanable {

    // MARK: - Atributos

    private StoreMaterial storeMaterial;

    private Integer id;

    // MARK: - Constructor

    public InventarioMaterial() {
        storeMaterial = new MemoryStoreMaterial();
    };

    // MARK: - Metodos Material

    public void agregarMaterial(Material material) {
        if (storeMaterial.contains(material))
            return;

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

    public Iterable<Material> buscarMaterial(MemorySpecification<Material> specification) {
        final List<Material> materials = new ArrayList<>();

        for (Material material: storeMaterial.findAll()) {
            if (specification.test(material))
                materials.add(material);
        }

        return materials;
    }

    @Override
    public BigDecimal calcularCosto() {
        Iterator<Material> iterator = storeMaterial.findAll().iterator();

        BigDecimal costoTotal = new BigDecimal(0);

        while (iterator.hasNext())
            costoTotal = costoTotal.add(iterator.next().calcularCosto());

        return costoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void clean() {
        storeMaterial.clean();
        storeMaterial = null;
    }
}
