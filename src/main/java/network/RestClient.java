package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public abstract class RestClient {

    private final AsyncHttpClient asyncHttpClient;

    public RestClient() {
        asyncHttpClient = new DefaultAsyncHttpClient();
    }

    protected JsonElement request(URLRequestConvertible urlRequest, JsonObject parameters) throws NetworkException, IOException {

        String URL = urlRequest.baseURL() + urlRequest.path();

        CompletableFuture<Response> future = asyncHttpClient
                .prepare(urlRequest.method(), URL)
                .setHeaders(urlRequest.headers())
                .setBody(parameters.toString())
                .execute()
                .toCompletableFuture();

        Response response = future.join();

        JsonElement result = null;

        validateStatusCode(response);

        if (response.getContentType() == null) result = null;
        else result = new JsonParser().parse(response.getResponseBody());

        return result;
    }

    private void validateStatusCode(Response response) throws NetworkException {
        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) return;

        JsonElement parser = new JsonParser().parse(response.getResponseBody());

        JsonObject json = parser.getAsJsonObject();

        throw new NetworkException(response.getStatusCode(), json.get("message").getAsString());
    }

}
