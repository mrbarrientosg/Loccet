package view;

import base.Fragment;
import base.Injectable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import router.ProyectoRouter;
import router.RRHHRouter;

import java.io.IOException;

public class TableroView extends Fragment {

    @FXML
    private Label titleBar;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private ToggleGroup menuToggleGroup;

    @FXML
    private ToggleButton inicioButton;

    @FXML
    private ToggleButton rrhhButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button maximizeButton;

    @FXML
    private Button exitButton;

    @FXML
    private ToggleButton proyectoButton;

    private Toggle lastSelected;

    private double xOffset = 0;

    private double yOffset = 0;

    @Override
    public void viewDidLoad() {
        getRoot().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        getRoot().setOnMouseDragged(event -> {
            if (yOffset <= 100) {
                getPrimaryStage().setX(event.getScreenX() - xOffset);
                getPrimaryStage().setY(event.getScreenY() - yOffset);
            }
        });

        menuToggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
            else {
                if (lastSelected != null && lastSelected != newVal) {
                    if (!contentPane.getChildren().isEmpty())
                        contentPane.getChildren().remove(0);
                    setupCenter(newVal);
                } else if (lastSelected == null) {
                    setupCenter(newVal);
                }

                lastSelected = newVal;
            }
        });

        maximizeButton.setOnAction(event -> {
            // TODO: Verificar maximizar la vista, no funciona
            //getCurrentStage().setMaximized(true);
        });

        minimizeButton.setOnAction(event -> {
            getCurrentStage().setIconified(true);
        });

        exitButton.setOnAction(event ->  {
            Platform.exit();
            System.exit(0);
        });
    }

    private void setCenter(Parent node) {
        contentPane.getChildren().add(node);
        AnchorPane.setBottomAnchor(node, 16.0);
        AnchorPane.setTopAnchor(node, 16.0);
        AnchorPane.setLeftAnchor(node, 16.0);
        AnchorPane.setRightAnchor(node, 16.0);
    }

    private void setupCenter(Toggle button) {
        /*
         Task<Parent> loadTask = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                return Injectable.find(RRHHView.class).getRoot();
            }
        };

        loadTask.setOnSucceeded(event -> {
            if (button == rrhhButton) {
                setCenter(loadTask.getValue());
            }
        });

        new Thread(loadTask).start();
         */

        if (button == rrhhButton) {
            RRHHView rrhhView = RRHHRouter.create();
            setCenter(rrhhView.getRoot());
        }else if(button == proyectoButton){
            ProyectoView proyectoView = ProyectoRouter.create();
            setCenter(proyectoView.getRoot());
        }
    }
}
