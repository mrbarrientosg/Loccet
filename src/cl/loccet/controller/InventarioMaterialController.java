package cl.loccet.controller;
import cl.loccet.base.Controller;
import cl.loccet.model.InventarioMaterial;
import cl.loccet.model.Material;
import cl.loccet.router.InventarioMaterialRouter;
import cl.loccet.view.InventarioMaterialView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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

    private void cargarDatos() {
        materials = FXCollections.observableList(model.obtenerMateriales());
    }

    public ObservableList<Material> obtenerDatos() {
        return materials;
    }

    public void nuevoMaterial(Material material){
        model.nuevoItem(material);
        cargarDatos();
    }
    public void agregarMaterial(String idMaterial,int cantidad){
        model.agregarMaterial(idMaterial,cantidad);
        cargarDatos();
    }
    public void retirarMaterial(String idMaterial,int cantidad){
        model.retirarMaterial(idMaterial,cantidad);
        cargarDatos();
    }

    public void modificarNombre(String idMaterial, String nombre){
        model.modificarNombre( idMaterial,nombre);
    }

    public void modificarDescripcion(String idMaterial,String descripcion){
        model.modificarDescripcion( idMaterial,descripcion);
    }


    public void eliminarMaterial(String idMaterial){
        model.eliminarItem(idMaterial);
    }

}
