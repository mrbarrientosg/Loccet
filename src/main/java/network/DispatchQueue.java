package network;

import com.sun.glass.ui.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DispatchQueue<T> extends Task<T> {

    private ReadOnlyBooleanWrapper internalCompleted = new ReadOnlyBooleanWrapper(false);

    private Function<DispatchQueue, T> func;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public DispatchQueue(Function<DispatchQueue, T> func) {
        this.func = func;
        executorService.execute(this);
    }

    public void value(T value) {
        super.updateValue(value);
    }

    public Task<T> success(Consumer<T> func) {
        if (Application.isEventThread())
            attachSuccessHandler(func);
        else
            Platform.runLater(() -> {
                attachSuccessHandler(func);
            });

        return this;
    }

    private void attachSuccessHandler(Consumer<T> func) {
        if (getState() == Worker.State.SUCCEEDED) {
            func.accept(getValue());
        } else {
            setOnSucceeded(event -> {
                func.accept(getValue());
            });
        }
    }

    @Override
    protected void succeeded() {
        internalCompleted.setValue(true);
        executorService.shutdown();
    }

    @Override
    protected void failed() {
        internalCompleted.setValue(true);
        executorService.shutdown();
    }

    @Override
    protected void cancelled() {
        internalCompleted.setValue(true);
        executorService.shutdown();
    }

    @Override
    protected T call() throws Exception {
        return func.apply(this);
    }

    public ReadOnlyBooleanProperty completedProperty() {
        return internalCompleted.getReadOnlyProperty();
    }

    public boolean completed() {
        return internalCompleted.getValue();
    }
}
