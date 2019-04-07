package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.HomeController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class HomeView extends View {

    private HomeController controller;

    public void setController(HomeController controller) {
        this.controller = controller;
    }

    public HomeView() {
        super("General");
    }

    @Override
    public void viewDidLoad() {

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

    public void setCenter(Parent node) {
        getRoot().setCenter(node);
        getCurrentStage().sizeToScene();
    }

    public void setRight(Parent node) {
        getRoot().setRight(node);
        getCurrentStage().sizeToScene();
    }

    public void setTop(Parent node) {
        getRoot().setTop(node);
    }

}
