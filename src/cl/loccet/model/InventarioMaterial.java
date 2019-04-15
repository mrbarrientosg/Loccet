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
    public void agregarMaterial(String idMaterial,double cantidad){
        Material material = mapInventarios.get(idMaterial);
        material.setCantidad(material.getCantidad()+cantidad);
        material.setFechaIngreso(new Date());
    }
    public void retirarMaterial(String idMaterial,double cantidad){
        Material material = mapInventarios.get(idMaterial);
        material.setCantidad(material.getCantidad()-cantidad);
        material.setFechaRetiro(new Date());
        material.setRetiro(cantidad);
    }

    public void modificarNombre(String idMaterial,String nombre){
        Material material = mapInventarios.get(idMaterial);
        material.setNombre(nombre);
    }
    public void modificarDescripcion(String idMaterial,String descripcion){
        Material material = mapInventarios.get(idMaterial);
        material.setDescripcion(descripcion);
    }

    public void eliminarItem(String idMaterial){
        Material material = mapInventarios.get(idMaterial);
        mapInventarios.remove(idMaterial);
        listaInventarios.remove(material);
    }


}
