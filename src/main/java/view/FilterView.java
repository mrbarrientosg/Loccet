package view;

import base.Fragment;
import cell.FilterCell;
import delegate.FilterCellDelegate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import delegate.FilterDelegate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class FilterView extends Fragment implements FilterCellDelegate {

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    @FXML
    private GridPane content;

    @FXML
    private Button addFilter;

    private ObservableList<FilterCell> filterCells;

    private ObservableList<Pair<String, Class<?>>> columnList;

    private int lastRow;

    private FilterDelegate delegate;

    @Override
    public void viewDidLoad() {
        lastRow = 1;
        addFilter.setOnAction(this::addFilterAction);
        applyButton.setOnAction(this::applyAction);
        cancelButton.setOnAction(this::closeAction);
    }

    @Override
    public void onDelete(FilterCell cell) {
        content.getChildren().removeAll(cell.getChildrens());

        filterCells.remove(cell);

        lastRow -= 1;

        delegate.filters(getConditions());
    }

    private void addFilterAction(ActionEvent event) {
        FilterCell cell = new FilterCell(columnList);
        cell.setDelegate(this);
        addCell(cell);

        filterCells.add(cell);
    }

    private void applyAction(ActionEvent event) {
        filterCells.removeIf(filterCell -> filterCell.getColumnName() == null || filterCell.getFilter() == null);
        delegate.filters(getConditions());
        close();
    }

    private void closeAction(ActionEvent event) {
        filterCells.clear();
        close();
    }

    private void setupFilters() {
        filterCells.forEach(filterCell -> {
            filterCell.setDelegate(this);
            addCell(filterCell);
        });
    }

    private void addCell(FilterCell cell) {
        content.addRow(lastRow, cell.getChildrens());
        lastRow += 1;
    }

    private Map<String, Predicate> getConditions() {
        Map<String, Predicate> map = new HashMap<>();

        filterCells.forEach(cell -> {
            if (map.containsKey(cell.getColumnName())) {
                Predicate predicate = map.get(cell.getColumnName()).and(cell.getFilter());
                map.put(cell.getColumnName(), predicate);
            } else {
                map.put(cell.getColumnName(), cell.getFilter());
            }
        });

        return map;
    }

    public void setFilterCells(ObservableList<FilterCell> filterCells) {
        this.filterCells = filterCells;
        setupFilters();
    }

    public void setColumnList(ObservableList<Pair<String, Class<?>>> columnList) {
        this.columnList = columnList;
    }

    public void setDelegate(FilterDelegate delegate) {
        this.delegate = delegate;
    }
}
