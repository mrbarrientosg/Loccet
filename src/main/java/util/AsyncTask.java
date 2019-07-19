package util;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AsyncTask<T> extends Task<T> {

    private Supplier<T> supplier;

    public AsyncTask(Supplier<T> supplier) {
        super();
        this.supplier = supplier;
        ThreadPools.getInstance().fxThreadPool().execute(this);
    }

    public static <T> AsyncTask<T> supplyAsync(Supplier<T> supplier) {
        return new AsyncTask<>(supplier);
    }

    @Override
    protected T call() throws Exception {
        return supplier.get();
    }

    public AsyncTask<T> thenAccept(Consumer<T> consumer) {
        Runnable attachSuccessHandler = () -> {
            if (getState() == Worker.State.SUCCEEDED) {
                consumer.accept(getValue());
            } else {
                setOnSucceeded(event -> {
                    consumer.accept(getValue());
                });
            }
        };

        if (Platform.isFxApplicationThread())
            attachSuccessHandler.run();
        else
            Platform.runLater(attachSuccessHandler);

        return this;
    }


}
