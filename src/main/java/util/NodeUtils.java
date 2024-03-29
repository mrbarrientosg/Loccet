package util;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public final class NodeUtils {

    public static void replaceWith(Node from, Node to, Boolean sizeToScene, Boolean centerOnScreen, Runnable onTransit) {
        if (onTransit != null) onTransit.run();

        if (from.getScene() != null && from == from.getScene().getRoot()) {
            Scene scene = from.getScene();

            scene.setRoot((Parent) to);
            if (sizeToScene) scene.getWindow().sizeToScene();
            if (centerOnScreen) scene.getWindow().centerOnScreen();

        } else if (from.getParent() instanceof Pane) {
            attach(from, to);
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
