package network.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.Maybe;
import network.NetworkException;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class NetService<EndPoint extends EndPointType> implements NetworkRouter<EndPoint> {

    private AsyncHttpClient asyncHttpClient;

    private CompletableFuture<Response> response;

    private static NetService instance;

    private NetService() {
        asyncHttpClient = new DefaultAsyncHttpClient();
    }

    public static NetService getInstance() {
        if (instance == null)
            instance = new NetService();
        return instance;
    }

    @Override
    public Maybe<JsonElement> request(EndPoint route, JsonObject parameters) {
        return Maybe.create(maybeEmitter -> {
            this.response = buildRequest(route, parameters)
                    .execute()
                    .toCompletableFuture();

            Response response = this.response.join();

            validateStatusCode(response);

            if (response.getContentType() == null) maybeEmitter.onSuccess(null);
            else maybeEmitter.onSuccess(new JsonParser().parse(response.getResponseBody()));

        }).timeout(20, TimeUnit.SECONDS).map(object -> (JsonElement) object);
    }

    @Override
    public void close() throws IOException {
        asyncHttpClient.close();
        asyncHttpClient = null;
    }

    private BoundRequestBuilder buildRequest(EndPoint route, JsonObject parameters) {
        BoundRequestBuilder request = asyncHttpClient
                .prepare(route.httpMethod().name(), route.baseURL().concat(route.path()));

        request.setBody(parameters.toString());

        request.setHeaders(route.httpHeaders());

        return request;
    }

    private void validateStatusCode(Response response) throws NetworkException {
        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) return;

        JsonElement parser = new JsonParser().parse(response.getResponseBody());

        JsonObject json = parser.getAsJsonObject();

        throw new NetworkException(response.getStatusCode(), json.get("message").getAsString());
    }
}
