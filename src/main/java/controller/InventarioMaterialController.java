package controller;

import base.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InventarioMaterial;
import model.Material;
import router.InventarioMaterialRouter;
import view.InventarioMaterialView;

/**
 *Clase manejadora de las funciones de la vista inventario.
 *
 * @author Sebastian Fuenzalida.
 */
public class InventarioMaterialController extends Controller {

    private InventarioMaterialView view;

    private InventarioMaterialRouter router;

    private InventarioMaterial model;

    private ObservableList<Material> materials;

    public InventarioMaterialController(InventarioMaterialView view, InventarioMaterial model, InventarioMaterialRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
        cargarDatos();
    }

    /**
     * Se obtienen los datos cargados del modelo.
     *
     * @author Sebastian Fuenzalida.
     */
    private void cargarDatos() {
        materials = FXCollections.observableList(model.obtenerMateriales());
    }

    /**
     * Se retornan los datos obtenidos previamente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @return
     */
    public ObservableList<Material> obtenerDatos() {
        return materials;
    }

    public void nuevoMaterial(Material material){
        model.nuevoItem(material);
        cargarDatos();
    }

    public void agregarMaterial(String idMaterial, double cantidad){
        model.agregarMaterial(idMaterial, cantidad);
        cargarDatos();
    }

    public void retirarMaterial(String idMaterial, double cantidad){
        model.retirarMaterial(idMaterial, cantidad);
        cargarDatos();
    }

    public void modificarNombre(String idMaterial, String nombre){
        model.modificarNombre(idMaterial, nombre);
    }

    public void modificarDescripcion(String idMaterial, String descripcion){
        model.modificarDescripcion(idMaterial, descripcion);
    }

    public void eliminarMaterial(String idMaterial){
        model.eliminarItem(idMaterial);
    }
}
