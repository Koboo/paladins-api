package eu.koboo.paladins.api.data.connectivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServerStatus {

    private static final SimpleDateFormat ENTRY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    long entryTimeStamp;
    ServerEnvironment environment;
    boolean limitedAccess;
    Platform platform;
    String statusText;
    String version;

    public ServerStatus(JsonObject object) {
        try {
            JsonElement dateTimeElement = object.get("entry_datetime");
            if (object.has("entry_datetime") && !object.get("entry_datetime").isJsonNull()) {
                entryTimeStamp = ENTRY_DATE_FORMAT.parse(dateTimeElement.getAsString()).getTime();
            } else {
                entryTimeStamp = -1;
            }
            environment = ServerEnvironment.parse(object.get("environment").getAsString());
            limitedAccess = object.get("limited_access").getAsBoolean();
            platform = Platform.parse(object.get("platform").getAsString());
            statusText = object.get("status").getAsString();
            version = object.get("version").getAsString();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
