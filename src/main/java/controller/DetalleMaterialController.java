package controller;

import base.Controller;
import cell.RegistroMaterialCell;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import delegate.EditMaterialDelegate;
import exceptions.EmptyFieldException;
import exceptions.NegativeQuantityException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Material;
import model.RegistroMaterial;
import network.endpoint.MaterialAPI;
import network.service.NetService;
import util.AsyncTask;
import view.DetalleMaterialView;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;


/**
 * @author Sebastian Fuenzalida
 */
public class DetalleMaterialController extends Controller {

    //Se declaran las variables

    private DetalleMaterialView view;

    private Material model;

    private Material oldMaterial;

    private NetService<MaterialAPI> service = NetService.getInstance();

    private EditMaterialDelegate delegate;

    public void obtenerRegistro(Consumer<ObservableList<RegistroMaterialCell>> callBack){
        AsyncTask.supplyAsync(() -> {
            ObservableList<RegistroMaterialCell> list = FXCollections.observableArrayList();

            model.getRegistrosMateriales().forEach(registroMaterial -> list.add(new RegistroMaterialCell(registroMaterial)));

            return list;
        }).thenAccept(callBack);
    }

    public void retirarMaterial(double cantidad) throws NegativeQuantityException {
        model.setCantidad(model.getCantidad() - cantidad);
        RegistroMaterial registroMaterial = new RegistroMaterial(cantidad,true);
        model.agregarRegistro(registroMaterial);
        view.cargarDatos();
        addRegistroMaterialBD(registroMaterial);
        updateMaterial();
    }

    public void agregarMaterial(double cantidad) throws NegativeQuantityException {
        model.setCantidad(model.getCantidad() + cantidad);
        RegistroMaterial registroMaterial = new RegistroMaterial(cantidad,false);
        model.agregarRegistro(registroMaterial);
        view.cargarDatos();
        addRegistroMaterialBD(registroMaterial);
        updateMaterial();
    }

    private void addRegistroMaterialBD(RegistroMaterial rm) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegistroMaterial.class, new RegistroMaterial.RegistroMaterialSerializer())
                .create();

        System.out.println(gson.toJsonTree(rm).getAsJsonObject());

        service.request(MaterialAPI.ADD_REGISTROMATERIAL, gson.toJsonTree(rm).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }


    public void save() {
        if (oldMaterial.equals(model))
            return;

        updateMaterial();
    }

    private void updateMaterial() {
        if (delegate != null)
            delegate.didEditMaterial(model);

        Gson gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        System.out.println(gson.toJsonTree(model).getAsJsonObject());

        service.request(MaterialAPI.UPDATE, gson.toJsonTree(model).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setView(DetalleMaterialView view) {
        this.view = view;
    }

    public void setModel(Material model){
        this.model = model;
        oldMaterial = new Material(model);
    }

    public void setDelegate(EditMaterialDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * @param descripcion material
     */
    public void modificarDescripcion(String descripcion) {
        model.setDescripcion(descripcion);
    }

    /**
     * @param nombre material
     */
    public void modificarNombre(String nombre) throws EmptyFieldException {
        model.setNombre(nombre);
    }


    /**
     * @return id
     */
    public String getID(){
        return model.getId();
    }

    /**
     * @return nombre material.
     */
    public String getNombre() {
        return model.getNombre();
    }

    /**
     * @return descripcion material
     */
    public String getDescripcion() {
        return model.getDescripcion();
    }

    /**
     * @return cantidad material
     */

    public Double getCantidad() {
        return model.getCantidad();
    }

    public String getPrecio() {
        return model.getPrecio().toString();
    }
}
