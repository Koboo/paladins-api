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
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter(AccessLevel.PROTECTED)
public class APIRequest {

    public static APIRequest create(HttpClient client) {
        return create(client, null);
    }

    public static APIRequest create(HttpClient client, APICredentials credentials) {
        return new APIRequest(client, credentials);
    }

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
    String playerName;
    @NonFinal
    long playerId;
    @NonFinal
    long timeStamp = -1;
    @NonFinal
    Hours hours;
    @NonFinal
    Minutes minutes;
    @NonFinal
    long matchId;
    @NonFinal
    List<Long> matchIdList;

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

    public APIRequest playerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public APIRequest playerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public APIRequest timeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public APIRequest hours(Hours hours) {
        this.hours = hours;
        return this;
    }

    public APIRequest minutes(Minutes minutes) {
        this.minutes = minutes;
        return this;
    }

    public APIRequest matchId(long matchId) {
        this.matchId = matchId;
        return this;
    }

    public APIRequest matchIdList(List<Long> matchIdList) {
        this.matchIdList = matchIdList;
        return this;
    }

    public String asString() {
        String methodName = method.getName();
        try {


            // Build the needed url
            String endpointURL = method.getURLBuilder().build(this);

            // Assign default values to the fields to reuse the request object
            sessionId = null;
            method = null;
            language = Language.ENGLISH;
            championId = -1;
            queue = -1;
            playerName = null;
            playerId = -1;
            timeStamp = -1;
            hours = null;
            minutes = null;
            matchId = -1;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpointURL))
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
