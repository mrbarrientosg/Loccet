package view;

import base.Fragment;
import base.Injectable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private ToggleButton proyectoButton;

    private Toggle lastSelected;

    private double xOffset = 0;

    private double yOffset = 0;

    @Override
    public void viewDidLoad() {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            if (yOffset <= 85) {
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
            RRHHView rrhhView = Injectable.find(RRHHView.class);
            setCenter(rrhhView.getRoot());
        }else if(button == proyectoButton){
            ProyectoView proyectoView = Injectable.find(ProyectoView.class);
            setCenter(proyectoView.getRoot());
        }
    }
}
