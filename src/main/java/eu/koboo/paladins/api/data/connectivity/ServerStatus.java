package eu.koboo.paladins.api.data.connectivity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
