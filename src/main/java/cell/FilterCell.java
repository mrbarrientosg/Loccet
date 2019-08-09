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

/**
 * Clase que contiene los componentes para poder filtrar
 * las tablas
 *
 * @author Matias Barrientos
 */
public final class FilterCell {
    private ComboBox<Pair<String, Class<?>>> columnName;

    private ComboBox<String> filter;

    private TextField value;

    private Button delete;

    private FilterCellDelegate delegate;

    private Parser parser;

    private Node[] childrens;

    public FilterCell(ObservableList<Pair<String, Class<?>>> columnList) {

        columnName = new ComboBox<>();
        columnName.setItems(columnList);
        columnName.prefWidth(-1);
        columnName.setMaxWidth(Double.MAX_VALUE);
        columnName.getStylesheets().add(getClass().getResource("../App.css").toString());
        columnName.getStyleClass().add("textfield-gray");
        columnName.getStyleClass().add("textfield");

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
            if (String.class.isAssignableFrom(newValue.getValue())) {
                parser = new StringParser(false);
            } else if (Number.class.isAssignableFrom(newValue.getValue())) {
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
        filter.getStylesheets().add(getClass().getResource("../App.css").toString());
        filter.getStyleClass().add("textfield-gray");
        filter.getStyleClass().add("textfield");

        value = new TextField();
        value.prefWidth(-1);
        value.getStylesheets().add(getClass().getResource("../App.css").toString());
        value.getStyleClass().add("textfield-gray");
        value.getStyleClass().add("textfield");

        delete = new Button("x");
        delete.getStylesheets().add(getClass().getResource("../App.css").toString());
        delete.getStyleClass().add("button-label-black");

        delete.setOnAction(this::onDelete);

        childrens = new Node[]{columnName, filter, value, delete};
    }

    private void onDelete(ActionEvent event) {
        if (delegate == null)
            return;

        delegate.onDelete(this);
    }

    public void setDelegate(FilterCellDelegate delegate) {
        this.delegate = delegate;
    }

    public Node[] getChildrens() {
        return childrens;
    }

    public String getColumnName() {
        if (columnName.getValue() == null)
            return null;

        return columnName.getValue().getKey();
    }

    public Predicate getFilter() {
        if (filter.getValue() == null)
            return null;

        StringBuilder builder = new StringBuilder(filter.getValue());

        if (parser instanceof NumberParser) {
            builder.append(value.getText());
        } else {
           builder.append(" \"")
                   .append(value.getText())
                   .append('\"');
        }


        return parser.parse(builder.toString());
    }
}
