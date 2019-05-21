package view;
import base.Fragment;
import controller.DetalleMaterialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.RegistroMaterial;
import router.DetalleMaterialRouter;

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
    private TableColumn<RegistroMaterial,String> fechaCL;
    @FXML
    private TableColumn<RegistroMaterial,Double> cantidadCL;
    @FXML
    private Button editarBT;
    @FXML
    private Button salirBT;


    @Override
    public void viewDidLoad() {
        mostrarDatos();
        inicializarTablaRegistro();
        editando = false;
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
    public void editar(ActionEvent event){

        if (!editando) { // Si la variable es falsa significa que aun no es presionado el boton editar.
            nombreTF.setDisable(false);// Se activar los textField editables
            descripcionTA.setDisable(false);
            editarBT.setText("Guardar");//Se cambia el boton editar a guardar.
        }else {//Si la variable es verdadera se comienza con el proceso de guardado.
            controller.ModificarDescripcion(descripcionTA.getText());//Se modifica la descripcion.
            controller.modificarNombre(nombreTF.getText());//Se modifca el nombre.
            nombreTF.setDisable(true);//Se desactivan los textField
            descripcionTA.setDisable(true);
            editarBT.setText("Editar");//Se regresa el boton a editar.
        }
    }

    /**
     *
     * Funcion que carga los datos en la tabla.
     *
     * @author Sebastian Fuenzalida.
     */
    private void inicializarTablaRegistro() {
        fechaCL.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
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

