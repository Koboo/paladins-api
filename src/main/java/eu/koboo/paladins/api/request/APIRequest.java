package eu.koboo.paladins.api.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.koboo.paladins.api.exceptions.MethodExecutionException;
import eu.koboo.paladins.api.utils.Validator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter(AccessLevel.PROTECTED)
public class APIRequest {

    public static APIRequest create(HttpClient client) {
        return create(client, null);
    }

    public static APIRequest create(HttpClient client, String devId, String authKey) {
        return create(client, new APICredentials(devId, authKey));
    }

    public static APIRequest create(HttpClient client, APICredentials credentials) {
        return new APIRequest(client, credentials);
    }

    //PaladinsAPI api;
    HttpClient client;
    APICredentials credentials;

    @NonFinal
    String sessionId;
    @NonFinal
    APIMethod method;
    @NonFinal
    Language language = Language.ENGLISH;
    @NonFinal
    long championId = -1;
    @NonFinal
    long queue = -1;
    @NonFinal
    String player;
    @NonFinal
    long playerId;

    public APIRequest session(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public APIRequest method(APIMethod method) {
        this.method = method;
        return this;
    }

    public APIRequest lang(Language language) {
        this.language = language;
        return this;
    }

    public APIRequest champion(long championId) {
        this.championId = championId;
        return this;
    }

    public APIRequest queue(long queue) {
        this.queue = queue;
        return this;
    }

    public APIRequest player(String player) {
        this.player = player;
        return this;
    }

    public APIRequest playerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public String asString() {
        String methodName = method.getName();
        try {
            // Build the needed url
            String url = method.url(this, language, championId, queue, player, playerId);

            System.out.println(url);

            // Assign default values to the fields to reuse the request object
            sessionId = null;
            method = null;
            language = Language.ENGLISH;
            championId = -1;
            queue = -1;
            player = null;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            throw new MethodExecutionException(methodName, e);
        }
    }

    public JsonObject asJsonObject() {
        String result = asString();
        if (Validator.isEmpty(result)) {
            return null;
        }
        return (JsonObject) JsonParser.parseString(result);
    }

    public JsonArray asJsonArray() {
        String result = asString();
        if (Validator.isEmpty(result)) {
            return null;
        }
        return (JsonArray) JsonParser.parseString(result);
    }

    public JsonObject asFirstJsonObject() {
        JsonArray array = asJsonArray();
        return (JsonObject) array.get(0);
    }

}
