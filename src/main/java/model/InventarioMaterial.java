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
public class InventarioMaterial implements Costeable{

    // MARK: - Atributos

    private RepositoryMaterial repositoryMaterial;

    // MARK: - Constructor

    public InventarioMaterial() {
        repositoryMaterial = new MemoryRepositoryMaterial();
    };

    // MARK: - Metodos Material

    public List<Material> obtenerMateriales() {
        List<Material> list = new ArrayList<>();
        repositoryMaterial.get().forEachRemaining(list::add);
        return list;
    }

    public void agregarMaterial(Material material) {
        repositoryMaterial.add(material);
    }

    public Material obtenerMaterial(String id){
       return repositoryMaterial.get(id);
    }

    public Material actualizarMaterial(Material material) {
        return repositoryMaterial.update(material);
    }

  /*  public BigDecimal costoInventario(){
        List<Material> list = obtenerMateriales();
        BigDecimal costoTotal = new BigDecimal(0);
        for (int i = 0; i < list.size();i++){
            costoTotal.add(list.get(i).getPrecio());
        }
        return costoTotal;
    }*/

    public Material eliminarMaterial(String id) {
        return repositoryMaterial.remove(repositoryMaterial.get(id));
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        repositoryMaterial.get(idMaterial).agregarRegistro(registroMaterial);
    }

    @Override
    public BigDecimal calcularCosto() {
        List<Material> list = obtenerMateriales();
        BigDecimal costoTotal = new BigDecimal(0);
        for (int i = 0; i < list.size();i++){
            costoTotal.add(list.get(i).getPrecio());
        }
        return costoTotal;
    }
}
