package view;

import base.Fragment;
import cell.TrabajadorCell;
import controller.BuscarTrabajadorController;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Trabajador;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BuscarTrabajadorView extends Fragment {

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
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchField.textProperty());

        textInputs
                .defaultIfEmpty(null)
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter(Objects::nonNull)
                .flatMap(value -> controller.searchEmployee(value)
                        .onErrorReturnItem(FXCollections.emptyObservableList())
                        .toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> {
                    listView.setItems(list);
                });


        /*searchField.setOnKeyReleased(event -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                controller.searchEmployee(newValue);
            });
            //refreshTable();
        });*/
    }

    @Override
    public void viewDidShow() {
        controller.selectedItemProperty().bind(listView.getSelectionModel().selectedItemProperty());

        doneButton.setOnAction(controller::doneAction);
        cancelButton.setOnAction(event -> close());
    }


    public void setController(BuscarTrabajadorController controller) {
        this.controller = controller;
    }
}
