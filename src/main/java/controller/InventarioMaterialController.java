package controller;

import base.Controller;
import cell.MaterialCell;
import com.google.gson.*;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import network.endpoint.MaterialAPI;
import network.service.NetService;
import specification.MaterialByQuerySpecification;
import util.ExportFile.ExportFile;
import util.InventarioExport.ExportInventarioPDF;
import util.InventarioExport.ExportInventarioXLSX;
import view.InventarioMaterialView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Clase manejadora de las funciones de la vista inventario.
 *
 * @see view.InventarioMaterialView
 *
 * @author Sebastian Fuenzalida.
 */
public final class InventarioMaterialController extends Controller {

    private InventarioMaterialView view;

    private Constructora model;

    private String idProyecto;

    private NetService service;

    private ExportFile exportFile;

    public InventarioMaterialController() {
        model = Constructora.getInstance();
        service = NetService.getInstance();
        exportFile = new ExportFile();
    }

    public ObservableList<MaterialCell> searchMaterials(String text) {
        ObservableList<MaterialCell> cells = FXCollections.observableArrayList();

        StreamSupport.stream(model.buscarMaterial(idProyecto, new MaterialByQuerySpecification(text)).spliterator(), false)
                .map(MaterialCell::new)
                .forEach(cells::add);

        return cells;
    }

    public ObservableList<String> fetchUnidades() {
        return FXCollections.observableArrayList(Arrays.stream(UnidadMedida.values())
                .map(UnidadMedida::getValue)
                .collect(Collectors.toList()));
    }

    public Material getMaterial(String id){
        return model.obtenerMaterial(idProyecto, id);
    }

    /**
     * Crea un nuevo material al modelo
     * @param material nuevo material a agregar
     */
    public void nuevoMaterial(Material material, RegistroMaterial rm) {
        material.agregarRegistro(rm);
        model.agregarMaterial(idProyecto, material);
        view.didAddMaterial(new MaterialCell(material));

        Gson gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(material).getAsJsonObject();
        json.addProperty("id_inventario", model.getIdInventario(idProyecto));

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
        MaterialCell materialCell = new MaterialCell(model.eliminarMaterial(idProyecto, idMaterial));

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
     * @param extension extension que va a guardar (.pdf, .xlxs)
     * @param dest archivo de destino para guardar
     * @throws IOException El archivo no se puedo crear o guardar
     */
    public void guardarArchivoInventario(String extension, File dest, ObservableList<MaterialCell> list) throws IOException, DocumentException {
        Proyecto p = model.obtenerProyecto(idProyecto);

        if (extension.equals("*.pdf")) {
            exportFile.changeStrategy(new ExportInventarioPDF(p.getNombre(), list));
        } else {
            exportFile.changeStrategy(new ExportInventarioXLSX(p.getNombre(), list));
        }

        File file = exportFile.export();

        Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }


    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setView(InventarioMaterialView view) {
        this.view = view;
    }

    public String getIdProyecto() {
        return idProyecto;
    }
}
