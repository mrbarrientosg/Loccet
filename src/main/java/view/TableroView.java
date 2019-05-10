package view;

import base.Fragment;

public class TableroView extends Fragment {

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
    }
}
