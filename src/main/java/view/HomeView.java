package view;

import base.View;
import controller.HomeController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public final class HomeView extends View {

    private HomeController controller;

    public HomeView() {
        super("General");
    }

    @Override
    public void viewDidLoad() {
        getRoot().setCenter(new Pane());
        getRoot().centerProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) getRoot().setCenter(new Pane());
            Platform.runLater(() -> {
                getCurrentStage().sizeToScene();
                getCurrentStage().centerOnScreen();
            });
        }));
    }

    @Override
    public void viewDidClose() {

    }

    @Override
    public BorderPane getRoot()  {
        return (BorderPane) root;
    }

    public void removeNode(Node node) {
        getRoot().getChildren().remove(node);
    }

    public void setTop(Parent node) {
        getRoot().setTop(node);
    }

    public void setController(HomeController controller) {
        this.controller = controller;
    }

}
