package json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Instant;

/**
 * Clase que serializa y deserializa un tipo de dato Instant
 * en formato json.
 */
public final class InstantTypeConverter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
    @Override
    public JsonElement serialize(Instant src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(src.getEpochSecond());
    }

    @Override
    public Instant deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return Instant.ofEpochSecond(json.getAsLong());
    }
}