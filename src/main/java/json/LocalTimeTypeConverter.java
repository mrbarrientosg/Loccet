package json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;

public final class LocalTimeTypeConverter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalTime.parse(json.getAsString());
    }

    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localTime.toString());
    }
}
