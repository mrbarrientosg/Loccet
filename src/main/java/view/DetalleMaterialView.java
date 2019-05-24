package view;
import base.Fragment;
import base.Injectable;
import controller.DetalleMaterialController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.RegistroMaterial;
import router.DetalleMaterialRouter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Clase vista del detalle de un material.
 *
 * @author Sebastian Fuenzalida.
 */
public class DetalleMaterialView extends Fragment {
    //Se declaran las variables.

    private DetalleMaterialController controller;
    private DetalleMaterialRouter router;
    private boolean editando;
    private ObservableList<RegistroMaterial> listaRegistro;

    @FXML
    private TextField idTF;
    @FXML
    private TextField nombreTF;
    @FXML
    private TextField cantidadTF;
    @FXML
    private TextArea  descripcionTA;
    @FXML
    private TableView<RegistroMaterial> tableView;
    @FXML
    private TableColumn<RegistroMaterial,Instant> fechaCL;
    @FXML
    private TableColumn<RegistroMaterial,Double> cantidadCL;
    @FXML
    private TableColumn<RegistroMaterial,Boolean> retiradoCL;
    @FXML
    private Button editarBT;
    @FXML
    private Button salirBT;
    @FXML
    private Button ingresarBT;
    @FXML
    private Button retirarBT;

    @Override
    public void viewDidLoad() {
        editando = false;
    }
    @Override
    public void viewDidShow(){
        inicializarTablaRegistro();
        cargarDatos();
        mostrarDatos();
    }

    private void refreshTable(){

        SortedList sortedList = new SortedList(listaRegistro);
        tableView.setItems(listaRegistro);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }
    public void cargarDatos(){
        listaRegistro = controller.obtenerRegistro();
        refreshTable();
    }

    /**
     * Funcion que muestra en la vista los datos del material.
     *
     * @author Sebastian Fuenzalida.
     */
    private void mostrarDatos(){
        idTF.setDisable(true);
        nombreTF.setDisable(true);
        cantidadTF.setDisable(true);
        descripcionTA.setDisable(true);

        idTF.setText(controller.getID());
        nombreTF.setText(controller.getNombre());
        cantidadTF.setText(Double.toString(controller.getCantidad()));
        descripcionTA.setText(controller.getDescripcion());
    }

    /**
     * Funcion que permite tanto comenzar un edit como guardar los cambios realizados.
     *
     * @param event Presionar boton editar/guardar
     *
     * @author Sebastian Fuenzalida.
     */
    @FXML
    public void editar(ActionEvent event){

        if (!editando) { // Si la variable es falsa significa que aun no es presionado el boton editar.
            nombreTF.setDisable(false);// Se activar los textField editables
            descripcionTA.setDisable(false);
            editarBT.setText("Guardar");//Se cambia el boton editar a guardar.
            editando = true;
        }else {//Si la variable es verdadera se comienza con el proceso de guardado.
            controller.ModificarDescripcion(descripcionTA.getText());//Se modifica la descripcion.
            controller.modificarNombre(nombreTF.getText());//Se modifca el nombre.
            nombreTF.setDisable(true);//Se desactivan los textField
            descripcionTA.setDisable(true);
            editarBT.setText("Editar");//Se regresa el boton a editar.
            editando = false;
        }
    }
    @FXML
    public void salir(ActionEvent event){
        close();
    }
    /**
     * Funcion que carga los datos en la tabla.
     *
     * @author Sebastian Fuenzalida.
     */
    private void inicializarTablaRegistro() {
        fechaCL.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        fechaCL.setCellFactory(column -> {
            TableCell<RegistroMaterial, Instant> cell = new TableCell<RegistroMaterial, Instant>() {
                private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                        .withLocale(Locale.getDefault())
                        .withZone(ZoneId.systemDefault());
                        //new DateTimeFormatter("dd-MM-yyyy HH:MM:ss");

                @Override
                protected void updateItem(Instant item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if (item != null) setText(format.format(item));
                        else setText(null);
                    }
                }
            };

            return cell;
        });
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        retiradoCL.setCellValueFactory(new PropertyValueFactory<>("retirado"));
        retiradoCL.setCellFactory(column -> {
            TableCell<RegistroMaterial, Boolean> cell = new TableCell<RegistroMaterial, Boolean>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if (!item) setText("Agregado");
                        else setText("Retirado");
                    }
                }
            };
            return cell;
        });
    }

    @FXML
    public void retirar(ActionEvent event){
        RetirarMaterialView view = Injectable.find(RetirarMaterialView.class);
        view.setController(controller);
        view.modal().withBlock(true).show();
        mostrarDatos();
    }
    @FXML
    public void agregar(ActionEvent event){
        AgregarMaterialView view = Injectable.find(AgregarMaterialView.class);
        view.setController(controller);
        view.modal().withBlock(true).show();
        mostrarDatos();
    }

    /**
     * @param controller detalleMaterial
     */
    public void setController(DetalleMaterialController controller) {
        this.controller = controller;
    }
    /**
     * @param router  detalleMaterial
     */
    public void setRouter(DetalleMaterialRouter router){this.router = router;}
}

