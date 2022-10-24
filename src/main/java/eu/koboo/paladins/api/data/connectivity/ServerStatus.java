package eu.koboo.paladins.api.data.connectivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ServerStatus {

    long entryTimeStamp;
    ServerEnvironment environment;
    boolean limitedAccess;
    String platform;
    String statusText;
    String version;
}
