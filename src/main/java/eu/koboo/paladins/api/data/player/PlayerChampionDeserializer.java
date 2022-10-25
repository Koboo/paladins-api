package eu.koboo.paladins.api.data.player;

import com.google.gson.*;
import eu.koboo.paladins.api.utils.DateFormatter;

import java.lang.reflect.Type;

public class PlayerChampionDeserializer implements JsonDeserializer<PlayerChampion> {

    @Override
    public PlayerChampion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        PlayerChampion playerChampion = new PlayerChampion();

        playerChampion.setPlayerId(Long.parseLong(object.get("player_id").getAsString()));
        playerChampion.setChampionId(Long.parseLong(object.get("champion_id").getAsString()));
        playerChampion.setLastTimePlayed(DateFormatter.parseFrom(object.get("LastPlayed").getAsString()));
        playerChampion.setRank(object.get("Rank").getAsLong());
        playerChampion.setMinutesPlayed(object.get("Minutes").getAsLong());

        playerChampion.setKills(object.get("Kills").getAsLong());
        playerChampion.setDeaths(object.get("Deaths").getAsLong());
        playerChampion.setAssists(object.get("Assists").getAsLong());
        playerChampion.setLosses(object.get("Losses").getAsLong());
        playerChampion.setWins(object.get("Wins").getAsLong());
        playerChampion.setWorshippers(object.get("Worshippers").getAsLong());
        playerChampion.setGold(object.get("Gold").getAsLong());
        playerChampion.setMinionKills(object.get("MinionKills").getAsLong());

        return playerChampion;
    }
}
