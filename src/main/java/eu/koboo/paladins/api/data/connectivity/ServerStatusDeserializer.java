package eu.koboo.paladins.api.data.connectivity;

import com.google.gson.*;
import eu.koboo.paladins.api.data.match.MatchPlayer;
import eu.koboo.paladins.api.utils.DateFormatter;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class ServerStatusDeserializer implements JsonDeserializer<ServerStatus> {

    @Override
    public ServerStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        ServerStatus serverStatus = new ServerStatus();

        JsonElement dateTimeElement = object.get("entry_datetime");
        if (object.has("entry_datetime") && !object.get("entry_datetime").isJsonNull()) {
            serverStatus.setEntryTimeStamp(DateFormatter.parseFromServerStatus(object.get("entry_datetime").getAsString()));
        } else {
            serverStatus.setEntryTimeStamp(-1);
        }
        serverStatus.setEnvironment(ServerEnvironment.parse(object.get("environment").getAsString()));
        serverStatus.setLimitedAccess(object.get("limited_access").getAsBoolean());
        serverStatus.setPlatform(object.get("platform").getAsString());
        serverStatus.setStatusText(object.get("status").getAsString());
        serverStatus.setVersion(object.get("version").getAsString());

        return serverStatus;
    }
}
