package cl.loccet.model;

import java.util.*;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial {

    private ArrayList<Material> listaInventarios;

    private HashMap<String, Material> mapInventarios;

    public InventarioMaterial() {
        listaInventarios = new ArrayList<>();
        mapInventarios = new HashMap<>();
    }

    public List<Material> obtenerMateriales() {
        return Collections.unmodifiableList(listaInventarios);
    }

    public void nuevoItem(Material item){
        Material itemInventario= mapInventarios.get(item.getId());
        if (itemInventario == null){
            mapInventarios.put(item.getId(),item);
            listaInventarios.add(item);
        }

    }
    public void agregarMaterial(Material material,int cantidad){
        material.setCantidad(material.getCantidad()+cantidad);
        material.setFechaIngreso(new Date());
    }
    public void retirarMaterial(Material material,int cantidad){
        material.setCantidad(material.getCantidad()-cantidad);
        material.setFechaRetiro(new Date());
        material.setRetiro(cantidad);
    }

    public void modificarNombre(Material material,String nombre){

        material.setNombre(nombre);

    }
    public void modificarDescripcion(Material material,String descripcion){
        material.setDescripcion(descripcion);
    }

    public void eliminarItem(Material material){
     listaInventarios.remove(material);
     mapInventarios.remove(material.getId());
    }


}
