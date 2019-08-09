package util;

import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class Alert {

    private javafx.scene.control.Alert alert;

    private Alert(AlertBuilder builder) {
        alert = new javafx.scene.control.Alert(builder.type);
        alert.setTitle(builder.title);
        alert.setContentText(builder.description);
        alert.setHeaderText(builder.header);
        alert.getButtonTypes().setAll(builder.buttonTypes);
    }

    public static AlertBuilder warning() {
        return new AlertBuilder(javafx.scene.control.Alert.AlertType.WARNING);
    }

    public static AlertBuilder error() {
        return new AlertBuilder(javafx.scene.control.Alert.AlertType.ERROR)
                .withTitle("Error");
    }

    public static AlertBuilder confirmation() {
        return new AlertBuilder(javafx.scene.control.Alert.AlertType.CONFIRMATION);
    }

    public static final class AlertBuilder {

        private javafx.scene.control.Alert.AlertType type;

        private String title;

        private String description;

        private String header;

        private List<ButtonType> buttonTypes;

        public AlertBuilder(javafx.scene.control.Alert.AlertType type) {
            this.type = type;
            buttonTypes = new ArrayList<>();
        }

        public AlertBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public AlertBuilder withHeader(String header) {
            this.header = header;
            return this;
        }

        public AlertBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public AlertBuilder withButton(ButtonType... buttons) {
            buttonTypes.addAll(Arrays.asList(buttons));
            return this;
        }

        public Alert build() {
            return new Alert(this);
        }
    }

    public void show() {
        alert.show();
    }

    public Optional<ButtonType> showAndWait() {
        return alert.showAndWait();
    }

    public void close() {
        alert.close();
    }
}
