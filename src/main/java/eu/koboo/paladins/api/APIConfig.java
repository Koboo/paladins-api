package eu.koboo.paladins.api;

import eu.koboo.paladins.api.request.APICredentials;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.function.Consumer;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
public class APIConfig {

    public static APIConfig of(String devId, String authKey) {
        return new APIConfig(new APICredentials(devId, authKey));
    }

    APICredentials credentials;

    @NonFinal
    String sessionId;

    @NonFinal
    Consumer<String> newSessionIdConsumer;

    // 30 sec. = Default timeout of most http clients
    @NonFinal
    int requestTimeoutSeconds = 30;

    // false = Don't check sessionId on startup
    @NonFinal
    boolean refreshSessionOnStartup = false;

    public APIConfig sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public APIConfig onNewSessionId(Consumer<String> newSessionIdConsumer) {
        this.newSessionIdConsumer = newSessionIdConsumer;
        return this;
    }

    public APIConfig requestTimeout(int requestTimeoutSeconds) {
        this.requestTimeoutSeconds = requestTimeoutSeconds;
        return this;
    }

    public APIConfig refreshSessionOnStartup(boolean value) {
        this.refreshSessionOnStartup = value;
        return this;
    }
}
