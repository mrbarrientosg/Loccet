package util;

import base.UIComponent;
import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class NodeUtils {

    public static void replaceWith(Node from, Node to, Boolean sizeToScene, Boolean centerOnScreen, Runnable onTransit) {
        if (onTransit != null) onTransit.run();

        if (from.getScene() != null && from == from.getScene().getRoot()) {
            Scene scene = from.getScene();


            //replacement.component.getCurrentStage().setScene(scene);
           // component.getCurrentStage().titleProperty().bind(replacement.component.getTitleProperty());

            //removeFromParent(from);
            //removeFromParent(to);
            //from.getParent().getChildrenUnmodifiable().remove(from);
            //to.getParent().getChildrenUnmodifiable().remove(to);
            scene.setRoot((Parent) to);
            if (sizeToScene) scene.getWindow().sizeToScene();
            if (centerOnScreen) scene.getWindow().centerOnScreen();

        } else if (from.getParent() instanceof Pane) {
            //removeFromParent(from);
            //removeFromParent(to);
            attach(from, to);
            //if (sizeToScene) component.getCurrentStage().sizeToScene();
            //if (centerOnScreen) component.getCurrentStage().centerOnScreen();
        }
    }

    private static void attach(Node from, Node replace) {
        Pane parent = (Pane) from.getParent();

        if (parent instanceof BorderPane) {
            if (((BorderPane) parent).getCenter() == from) {
                ((BorderPane) parent).setCenter(replace);
            }
        } else {
            int index = parent.getChildren().indexOf(from);
            parent.getChildren().add(index, replace);
        }
    }
    
}
