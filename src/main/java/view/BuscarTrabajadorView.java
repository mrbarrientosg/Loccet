package view;

import base.Fragment;
import cell.TrabajadorCell;
import controller.BuscarTrabajadorController;
import delegate.SearchEmployeeDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.util.concurrent.TimeUnit;

/**
 * Vista para buscar un trabajador
 *
 * @author Matias Barrientos
 */
public final class BuscarTrabajadorView extends Fragment {

    private BuscarTrabajadorController controller;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<TrabajadorCell> listView;

    @FXML
    private Button doneButton;

    @FXML
    private Button cancelButton;

    @Override
    public void viewDidLoad() {
        controller = new BuscarTrabajadorController();
        controller.setView(this);

        listView.setCellFactory(param -> new ListCell<TrabajadorCell>() {
            @Override
            protected void updateItem(TrabajadorCell item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getRut() + " " + item.getNombre() + " " + item.getApellido());
                }
            }
        });

        doneButton.setDisable(true);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                doneButton.setDisable(false);
            else
                doneButton.setDisable(true);
        });

        doneButton.setOnAction(controller::doneAction);
        cancelButton.setOnAction(event -> close());
    }

    @Override
    public void viewDidShow() {
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchField.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap(value -> controller.searchEmployee(value)
                        .onErrorReturnItem(FXCollections.emptyObservableList())
                        .toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(listView::setItems);

        controller.selectedItemProperty().bind(listView.getSelectionModel().selectedItemProperty());
    }

    public void display(SearchEmployeeDelegate delegate) {
        controller.setDelegate(delegate);
        modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }
}
