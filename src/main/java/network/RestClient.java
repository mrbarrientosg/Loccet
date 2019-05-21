package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.schedulers.Schedulers;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import io.reactivex.*;

public abstract class RestClient {

    private final AsyncHttpClient asyncHttpClient;

    public RestClient() {
        asyncHttpClient = new DefaultAsyncHttpClient();
    }

    protected Maybe<JsonElement> request(URLRequestConvertible urlRequest, JsonObject parameters) {
        return Maybe.create(maybe -> {
            String URL = urlRequest.baseURL() + urlRequest.path();

            CompletableFuture<Response> future = asyncHttpClient
                    .prepare(urlRequest.method(), URL)
                    .setHeaders(urlRequest.headers())
                    .setBody(parameters.toString())
                    .execute()
                    .toCompletableFuture();

            Response response = future.join();

            validateStatusCode(response);

            if (response.getContentType() == null) maybe.onSuccess(null);
            else maybe.onSuccess(new JsonParser().parse(response.getResponseBody()));
        });
    }

    private void validateStatusCode(Response response) throws NetworkException {
        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) return;

        JsonElement parser = new JsonParser().parse(response.getResponseBody());

        JsonObject json = parser.getAsJsonObject();

        throw new NetworkException(response.getStatusCode(), json.get("message").getAsString());
    }

}
