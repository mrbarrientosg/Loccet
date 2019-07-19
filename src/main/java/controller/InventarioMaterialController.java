package controller;

import base.Controller;
import cell.MaterialCell;
import com.google.gson.*;
import exceptions.ItemExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InventarioMaterial;
import model.Material;
import model.Proyecto;
import model.RegistroMaterial;
import network.endpoint.MaterialAPI;
import network.service.NetService;
import util.AsyncTask;
import util.ExportFile.ExportFile;
import util.InventarioExport.ExportInventarioPDF;
import util.InventarioExport.ExportInventarioXLSX;
import view.InventarioMaterialView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * Clase manejadora de las funciones de la vista inventario.
 *
 * @author Sebastian Fuenzalida.
 */
public final class InventarioMaterialController extends Controller {

    private InventarioMaterialView view;

    private InventarioMaterial model;

    private Proyecto proyecto;

    private NetService<MaterialAPI> service = NetService.getInstance();

    private ExportFile exportFile;

    /**
     * @param view  inventario material
     * @param model  inventario material
     */
    public InventarioMaterialController(InventarioMaterialView view, InventarioMaterial model) {
        this.view = view;
        this.model = model;
        exportFile = new ExportFile();
    }

    /**
     * Se obtienen los datos cargados del modelo.
     *
     * @author Sebastian Fuenzalida.
     */
    public void cargarDatos(Consumer<ObservableList<MaterialCell>> callBack) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<MaterialCell> list = FXCollections.observableArrayList();

            model.obtenerMateriales().forEach(material -> list.add(new MaterialCell(material)));

            return list;
        }).thenAccept(callBack);
    }


    public Material getMaterial(String id){
        return model.obtenerMaterial(id);
    }
    /**
     * Agrega un nuevo material al modelo
     * @param material nuevo material a agregar
     */
    public void nuevoMaterial(Material material, RegistroMaterial rm) throws ItemExisteException {
        material.agregarRegistro(rm);
        model.agregarMaterial(material);
        view.didAddMaterial(new MaterialCell(material));

        Gson gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(material).getAsJsonObject();
        json.addProperty("id_inventario", model.getId());

        System.out.println(json);

        service.request(MaterialAPI.CREATE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

        addRegistroMaterialBD(rm);
    }

    private void addRegistroMaterialBD(RegistroMaterial rm) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegistroMaterial.class, new RegistroMaterial.RegistroMaterialSerializer())
                .create();

        service.request(MaterialAPI.ADD_REGISTROMATERIAL, gson.toJsonTree(rm).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    /**
     * Elimina un material del modelo
     * @param idMaterial id del material a eliminar
     */
    public void eliminarMaterial(String idMaterial){
        MaterialCell materialCell = new MaterialCell(model.eliminarMaterial(idMaterial));

        view.removeMaterial(materialCell);

        JsonObject json = new JsonObject();
        json.addProperty("id_material", idMaterial);

        System.out.println(json);

        service.request(MaterialAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    /**
     * Guarda el archivo en la carpeta que seleccion el usuario
     * @param extension extension que esta usando
     * @param dest archivo de destino para guardar
     * @throws IOException
     */
    public void guardarArchivoInventario(String extension, File dest, ObservableList<MaterialCell> list) throws IOException {
        if (extension.equals("*.pdf")) {
            exportFile.changeStrategy(new ExportInventarioPDF(proyecto.getNombre(), list));
        } else {
            exportFile.changeStrategy(new ExportInventarioXLSX(proyecto.getNombre(), list));
        }

        File file = exportFile.export();

        if (file == null) {
            // TODO: Falta implementar el warning
            //router.showWarning("Error", "La exportación no se pudo guardar");
            return;
        }

        Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }


    /**
     * @param proyecto proyecto
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
