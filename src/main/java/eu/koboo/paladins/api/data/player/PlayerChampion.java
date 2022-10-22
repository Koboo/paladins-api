package eu.koboo.paladins.api.data.player;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.utils.DateFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerChampion {

    long playerId;
    long championId;
    long lastTimePlayed;
    long rank;
    long minutesPlayed;

    long kills;
    long deaths;
    long assists;
    long losses;
    long wins;
    long worshippers;
    long gold;
    long minionKills;

    public PlayerChampion(JsonObject object) {
        try {
            this.playerId = Long.parseLong(object.get("player_id").getAsString());
            this.championId = Long.parseLong(object.get("champion_id").getAsString());
            this.lastTimePlayed = DateFormatter.parseFrom(object.get("champion_id").getAsString());
            this.rank = object.get("Rank").getAsLong();
            this.minutesPlayed = object.get("Minutes").getAsLong();

            this.kills = object.get("Kills").getAsLong();
            this.deaths = object.get("Deaths").getAsLong();
            this.assists = object.get("Assists").getAsLong();
            this.losses = object.get("Losses").getAsLong();
            this.wins = object.get("Wins").getAsLong();
            this.worshippers = object.get("Total_Worshippers").getAsLong();
            this.gold = object.get("Gold").getAsLong();
            this.minionKills = object.get("MinionKills").getAsLong();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
