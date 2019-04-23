package model;

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

    /**
     *
     * Funcion que suma la cantidad de material ingresada por
     * el usuario al material correspondiente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param idMaterial
     * @param cantidad
     */
    public Material agregarMaterial(String idMaterial,double cantidad){
        Material material = mapInventarios.get(idMaterial);
        material.setCantidad(material.getCantidad()+cantidad);
        material.setFechaIngreso(new Date());
        return material;
    }

    /**
     * Funcion que resta la cantidad de material ingresada por
     * el usuario al material correspondiente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param idMaterial
     * @param cantidad
     */
    public Material retirarMaterial(String idMaterial,double cantidad){
        Material material = mapInventarios.get(idMaterial);
        material.setCantidad(material.getCantidad()-cantidad);
        material.setFechaRetiro(new Date());
        material.setRetiro(cantidad);
        return material;
    }

    /**
     * Funcion que modifica el nombre del material correspondiente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param idMaterial
     * @param nombre
     */
    public Material modificarNombre(String idMaterial,String nombre){
        Material material = mapInventarios.get(idMaterial);
        material.setNombre(nombre);
        return material;
    }

    /**
     * Funcion que modifica la descripcion del material correspondiente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param idMaterial
     * @param descripcion
     */
    public Material modificarDescripcion(String idMaterial,String descripcion){
        Material material = mapInventarios.get(idMaterial);
        material.setDescripcion(descripcion);
        return material;
    }

    /**
     * Funcion que elimina el material correspondiente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param idMaterial
     */
    public Material eliminarItem(String idMaterial){
        Material material = mapInventarios.remove(idMaterial);
        listaInventarios.remove(material);
        return material;
    }


    public double gastoTotal() {
        return listaInventarios.stream().map(Material::precioTotal).reduce(0.0, (left, right) -> left + right);
    }

}
