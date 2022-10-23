package eu.koboo.paladins.api.data.match;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.utils.DateFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchId {

    long matchId;
    long entryDateTime;
    String region;
    boolean active;

    public MatchId(JsonObject object) {
        try {
            this.matchId = Long.parseLong(object.get("Match").getAsString());
            this.entryDateTime = DateFormatter.parseFrom(object.get("Entry_Datetime").getAsString());
            this.region = object.get("Region").getAsString();
            this.active = object.get("Active_Flag").getAsString().equalsIgnoreCase("y");
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
