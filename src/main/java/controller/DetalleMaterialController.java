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
import model.Constructora;
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
public final class DetalleMaterialController extends Controller {

    //Se declaran las variables

    private DetalleMaterialView view;

    private Constructora model;

    private Material oldMaterial;

    private NetService service;

    private String idProyecto;

    private EditMaterialDelegate delegate;

    public DetalleMaterialController() {
        service = NetService.getInstance();
        model = Constructora.getInstance();
    }

    public void obtenerRegistros(Consumer<ObservableList<RegistroMaterialCell>> callBack){
        AsyncTask.supplyAsync(() -> {
            ObservableList<RegistroMaterialCell> list = FXCollections.observableArrayList();

            model.getRegistrosMateriales(idProyecto, oldMaterial.getId())
                    .forEach(registroMaterial -> list.add(new RegistroMaterialCell(registroMaterial)));

            return list;
        }).thenAccept(callBack);
    }

    public void retirarMaterial(double cantidad) throws NegativeQuantityException {
        model.actualizarCantidadMaterial(idProyecto, oldMaterial.getId(), -cantidad);

        RegistroMaterial registroMaterial = new RegistroMaterial(cantidad,true);
        model.agregarRegistroMaterial(idProyecto, oldMaterial.getId(), registroMaterial);

        view.agregarRegistroMaterial(new RegistroMaterialCell(registroMaterial));
        addRegistroMaterialBD(registroMaterial);
        updateMaterial();
    }

    public void agregarMaterial(double cantidad) throws NegativeQuantityException {
        model.actualizarCantidadMaterial(idProyecto, oldMaterial.getId(), cantidad);

        RegistroMaterial registroMaterial = new RegistroMaterial(cantidad,false);
        model.agregarRegistroMaterial(idProyecto, oldMaterial.getId(), registroMaterial);

        view.agregarRegistroMaterial(new RegistroMaterialCell(registroMaterial));
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
        if (oldMaterial.equals(model.obtenerMaterial(idProyecto, oldMaterial.getId())))
            return;

        updateMaterial();
    }

    private void updateMaterial() {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());

        if (delegate != null)
            delegate.didEditMaterial(m);

        Gson gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        System.out.println(gson.toJsonTree(m).getAsJsonObject());

        service.request(MaterialAPI.UPDATE, gson.toJsonTree(m).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setView(DetalleMaterialView view) {
        this.view = view;
    }

    public void setOldMaterial(Material old) {
        this.oldMaterial = new Material(old);
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setDelegate(EditMaterialDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * @param descripcion material
     */
    public void modificarDescripcion(String descripcion) {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());

        m.setDescripcion(descripcion);
    }

    /**
     * @param nombre material
     */
    public void modificarNombre(String nombre) throws EmptyFieldException {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());
        m.setNombre(nombre);
    }


    /**
     * @return id
     */
    public String getID(){
        return oldMaterial.getId();
    }

    /**
     * @return nombre material.
     */
    public String getNombre() {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());
        return m.getNombre();
    }

    /**
     * @return descripcion material
     */
    public String getDescripcion() {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());
        return m.getDescripcion();
    }

    /**
     * @return cantidad material
     */

    public Double getCantidad() {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());
        return m.getCantidad();
    }

    public String getPrecio() {
        Material m = model.obtenerMaterial(idProyecto, oldMaterial.getId());
        return m.getPrecio().toString();
    }
}
