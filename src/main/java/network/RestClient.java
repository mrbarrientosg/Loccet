package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public abstract class RestClient {

    private final AsyncHttpClient asyncHttpClient;

    public RestClient() {
        asyncHttpClient = new DefaultAsyncHttpClient();
    }

    protected Result<JsonElement> request(URLRequestConvertible urlRequest, JsonObject parameters) {

        String URL = urlRequest.baseURL() + urlRequest.path();

        CompletableFuture<Result<JsonElement>> future = asyncHttpClient
                .prepare(urlRequest.method(), URL)
                .setHeaders(urlRequest.headers())
                .setBody(parameters.toString())
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    Result<JsonElement> result;

                    try {
                        validateStatusCode(response);

                        if (response.getContentType() == null) result = Result.value(null);
                        else result = Result.value(new JsonParser().parse(response.getResponseBody()));
                    } catch (NetworkException e) {
                        result = Result.error(e);
                    }

                    return result;
                })
                .exceptionally(Result::error)
                .whenComplete((jsonElementResult, throwable) -> {
                    try {
                        asyncHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return future.join();
    }

    private void validateStatusCode(Response response) throws NetworkException {
        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) return;

        JsonElement parser = new JsonParser().parse(response.getResponseBody());

        JsonObject json = parser.getAsJsonObject();

        throw new NetworkException(response.getStatusCode(), json.get("message").getAsString());
    }

}
