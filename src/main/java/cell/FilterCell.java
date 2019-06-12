package cell;

import delegate.FilterCellDelegate;
import impl.org.controlsfx.tableview2.filter.parser.number.NumberParser;
import impl.org.controlsfx.tableview2.filter.parser.string.StringParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.Pair;
import org.controlsfx.control.tableview2.filter.parser.Parser;

import java.util.List;
import java.util.function.Predicate;

public class FilterCell {
    private ComboBox<Pair<String, Class<?>>> columnName;

    private ComboBox<String> filter;

    private TextField value;

    private Button delete;

    private FilterCellDelegate delegate;

    private Parser parser;

    public FilterCell(ObservableList<Pair<String, Class<?>>> columnList) {
        columnName = new ComboBox<>();
        columnName.setItems(columnList);
        columnName.prefWidth(-1);
        columnName.setMaxWidth(Double.MAX_VALUE);
        columnName.getStylesheets().add(getClass().getResource("../css/filter.css").toString());
        columnName.getStyleClass().add("gray-combo-box");
        columnName.getStyleClass().add("textFieldGrey");

        Callback<ListView<Pair<String, Class<?>>>, ListCell<Pair<String, Class<?>>>> factory = lv -> new ListCell<Pair<String, Class<?>>>() {
            @Override
            protected void updateItem(Pair<String, Class<?>> item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(item.getKey());
                }
            }
        };

        columnName.setCellFactory(factory);
        columnName.setButtonCell(factory.call(null));

        columnName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getValue().isAssignableFrom(String.class)) {
                parser = new StringParser(false);
            } else if (newValue.getValue().isAssignableFrom(Number.class)) {
                parser = new NumberParser();
            }

            List<String> list = parser.operators();
            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
            filter.setItems(FXCollections.observableArrayList(list));
        });

        filter = new ComboBox<>();
        filter.prefWidth(-1);
        filter.setMaxWidth(Double.MAX_VALUE);
        filter.getStylesheets().add(getClass().getResource("../css/filter.css").toString());
        filter.getStyleClass().add("gray-combo-box");
        filter.getStyleClass().add("textFieldGrey");

        value = new TextField();
        value.prefWidth(-1);
        value.getStylesheets().add(getClass().getResource("../css/filter.css").toString());
        value.getStyleClass().add("textFieldGrey");
        value.getStyleClass().add("textField");

        delete = new Button("x");
        delete.getStylesheets().add(getClass().getResource("../css/filter.css").toString());
        delete.getStyleClass().add("buttonWhite");

        delete.setOnAction(this::onDelete);
    }

    private void onDelete(ActionEvent event) {
        if (delegate == null)
            return;

        delegate.onDelete(this);
    }

    public void setDelegate(FilterCellDelegate delegate) {
        this.delegate = delegate;
    }

    public ObservableList<Node> getChildrens() {
        return FXCollections.observableArrayList(columnName, filter, value, delete);
    }

    public String getColumnName() {
        return columnName.getValue().getKey();
    }

    public Predicate getFilter() {
        if (filter.getValue() == null)
            return null;

        StringBuilder builder = new StringBuilder(filter.getValue())
                .append(" \"")
                .append(value.getText())
                .append('\"');

        return parser.parse(builder.toString());
    }
}