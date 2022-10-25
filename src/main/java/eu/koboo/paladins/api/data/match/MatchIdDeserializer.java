package eu.koboo.paladins.api.data.match;

import com.google.gson.*;
import eu.koboo.paladins.api.utils.DateFormatter;

import java.lang.reflect.Type;

public class MatchIdDeserializer implements JsonDeserializer<MatchId> {

    @Override
    public MatchId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        MatchId matchId = new MatchId();

        matchId.setMatchId(Long.parseLong(object.get("Match").getAsString()));
        matchId.setEntryDateTime(DateFormatter.parseFrom(object.get("Entry_Datetime").getAsString()));
        matchId.setRegion(object.get("Region").getAsString());
        matchId.setActive(object.get("Active_Flag").getAsString().equalsIgnoreCase("y"));

        return matchId;
    }
}
