package view;
import base.Fragment;
import controller.DetalleMaterialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.RegistroMaterial;
import router.DetalleMaterialRouter;


public class DetalleMaterialView extends Fragment {

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

    public void editar(ActionEvent event){
        if (editando == false ) {
            idTF.setDisable(false);
            nombreTF.setDisable(false);
            cantidadTF.setDisable(false);
            descripcionTA.setDisable(false);
            editarBT.setText("Guardar");
        }else {
            controller.ModificarDescripcion(descripcionTA.getText());
            controller.modificarNombre(nombreTF.getText());
            idTF.setDisable(true);
            nombreTF.setDisable(true);
            cantidadTF.setDisable(true);
            descripcionTA.setDisable(true);
            editarBT.setText("Editar");
        }
    }

    private void inicializarTablaRegistro() {
        fechaCL.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }
    public void setController(DetalleMaterialController controller) {
        this.controller = controller;
    }
    public void setRouter(DetalleMaterialRouter router){this.router = router;}
}

