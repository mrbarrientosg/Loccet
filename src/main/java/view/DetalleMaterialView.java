package view;

import base.Injectable;
import base.View;
import cell.RegistroMaterialCell;
import controller.DetalleMaterialController;
import exceptions.EmptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import router.DetalleMaterialRouter;

/**
 * Clase vista del detalle de un material.
 *
 * @author Sebastian Fuenzalida.
 */
public class DetalleMaterialView extends View {
    //Se declaran las variables.

    private DetalleMaterialController controller;

    private DetalleMaterialRouter router;

    private boolean editando;

    @FXML
    private TextField idTF;

    @FXML
    private TextField nombreTF;

    @FXML
    private TextField cantidadTF;

    @FXML
    private TextArea  descripcionTA;

    @FXML
    private TextField precioTF;

    @FXML
    private TableView<RegistroMaterialCell> tableView;

    @FXML
    private TableColumn<RegistroMaterialCell, String> fechaCL;

    @FXML
    private TableColumn<RegistroMaterialCell, String> cantidadCL;

    @FXML
    private TableColumn<RegistroMaterialCell, String> retiradoCL;

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

    @Override
    public void viewDidClose() {
        controller.save();
    }


    public void cargarDatos(){
        controller.obtenerRegistro(tableView::setItems);
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
        precioTF.setDisable(true);

        idTF.setText(controller.getID());
        precioTF.setText(controller.getPrecio());
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
        } else {//Si la variable es verdadera se comienza con el proceso de guardado.
            try {
                controller.modificarDescripcion(descripcionTA.getText());//Se modifica la descripcion.
                controller.modificarNombre(nombreTF.getText());//Se modifca el nombre.
                nombreTF.setDisable(true);//Se desactivan los textField
                descripcionTA.setDisable(true);
                editarBT.setText("Editar");//Se regresa el boton a editar.
                editando = false;
            } catch (EmptyFieldException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }

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
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        retiradoCL.setCellValueFactory(new PropertyValueFactory<>("retirado"));
    }

    @FXML
    public void retirar(ActionEvent event){
        RetirarMaterialView view = Injectable.find(RetirarMaterialView.class);
        view.setController(controller);
        view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
        mostrarDatos();
    }

    @FXML
    public void agregar(ActionEvent event){
        AgregarMaterialView view = Injectable.find(AgregarMaterialView.class);
        view.setController(controller);
        view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
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

