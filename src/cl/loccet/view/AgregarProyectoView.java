package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.AgregarProyectoController;
import cl.loccet.model.Constructora;
import cl.loccet.model.Proyecto;
import cl.loccet.router.AgregarProyectoRouter;
import com.sun.media.jfxmedia.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Matias Zuñiga
 * Establece la vista de agregar proyecto al momento de presionarlo en el menuBar
 */

public class AgregarProyectoView extends View {

    private AgregarProyectoController controller;

    //Botones
    @FXML private Button cancelar;
    @FXML private Button sTrabajadores;
    @FXML private Button aceptar;
    @FXML private TextField nombreP;
    @FXML private TextField jefeP;
    @FXML private TextField montoC;
    @FXML private TextField cliente;
    @FXML private TextField telefonoC;
    @FXML private TextField mailC;
    @FXML private TextField direccion;
    @FXML private TextField ciudad;
    @FXML private TextField estado;
    @FXML private TextField pais;
    @FXML private DatePicker fechaF;
    @FXML private DatePicker fechaT;

    @Override public void viewDidLoad() {
    }

    @Override public void viewDidClose() {
        System.out.println("viewDidClose");
        nombreP.setText("");
        jefeP.setText("");
        mailC.setText("");
        montoC.setText("");
        ciudad.setText("");
        cliente.setText("");
        telefonoC.setText("");
        estado.setText("");
        pais.setText("");
        direccion.setText("");
    }
    @FXML private void apretarAceptar(){
        if(nombreP.getText().isEmpty() || jefeP.getText().isEmpty() || montoC.getText().isEmpty()|| cliente.getText().isEmpty()|| telefonoC.getText().isEmpty() || mailC.getText().isEmpty() || direccion.getText().isEmpty() ||ciudad.getText().isEmpty()||estado.getText().isEmpty()||pais.getText().isEmpty() ||
                fechaF.getEditor().getText().isEmpty() || fechaT.getEditor().getText().isEmpty()){
                Alert alert = controller.getRouter().showAlert("Existen casillas sin rellenar!");
                Optional<ButtonType> result = alert.showAndWait();
                System.out.println("campos vacios");
        }
        else{
            LOGGER.info("Nombre proyecto: " + nombreP.getText());
            LOGGER.info("Jefe proyecto: " + jefeP.getText());
            LOGGER.info("Cliente: " + cliente.getText());
            LOGGER.info("Presupuesto: " + montoC.getText());
            LOGGER.info("Mail Cliente: " + mailC.getText());
            LOGGER.info("Telefono Cliente: " + telefonoC.getText());
            LOGGER.info("Direccion: " + direccion.getText());
            LOGGER.info("Pais: " + pais.getText());
            LOGGER.info("Estado: " + estado.getText());
            LOGGER.info("Ciudad: " + ciudad.getText());
            System.out.println("Todos los campos estan llenos");
            Proyecto proyecto = new Proyecto(controller.generarId(),nombreP.getText(),jefeP.getText(),mailC.getText(),telefonoC.getText(),direccion.getText(),pais.getText(),ciudad.getText(),estado.getText(),fechaF.getEditor().getText(),fechaT.getEditor().getText(),Double.parseDouble(montoC.getText()));
            Constructora constructora = new Constructora("RUT", "NOMBRE");
            constructora.agregarProyecto(proyecto);
            System.out.println("nombre:" + proyecto.getNombreProyecto() + "id: " + proyecto.getId());
            //TODO: No guarda aún trabajadores

        }
    }
    @FXML private void apretarCancelar(){
        Alert alert = controller.getRouter().showWarning("Esta seguro que desea cancelar?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if(result.get() == ButtonType.OK){
                close();
                Injectable.find(HomeView.class).window().show();
            }
            else{
                alert.close();
            }
        }
    }
    public void setController(AgregarProyectoController controller) {
        this.controller = controller;
    }
    @FXML private void seleccionarTrabajadores(){

    }
}
