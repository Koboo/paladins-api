
package eu.koboo.paladins.api.data.champion.leaderboard;

import com.google.gson.*;
import eu.koboo.paladins.api.data.connectivity.DataStats;

import java.lang.reflect.Type;

public class BoardRankDeserializer implements JsonDeserializer<BoardRank> {

    @Override
    public BoardRank deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        BoardRank boardRank = new BoardRank();

        boardRank.setChampionId(Long.parseLong(object.get("champion_id").getAsString()));

        boardRank.setPlayerId(Long.parseLong(object.get("player_id").getAsString()));
        boardRank.setPlayerName(object.get("player_name").getAsString());
        boardRank.setPlayerRanking(Double.parseDouble(object.get("player_ranking").getAsString()));

        boardRank.setRank(Long.parseLong(object.get("rank").getAsString()));
        boardRank.setWins(Long.parseLong(object.get("wins").getAsString()));
        boardRank.setLosses(Long.parseLong(object.get("losses").getAsString()));

        return boardRank;
    }
}
