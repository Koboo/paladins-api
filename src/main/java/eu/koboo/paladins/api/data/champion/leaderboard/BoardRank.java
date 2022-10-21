package eu.koboo.paladins.api.data.champion.leaderboard;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardRank {

    long championId;

    long playerId;
    String playerName;
    double playerRanking;

    long rank;
    long wins;
    long losses;

    public BoardRank(JsonObject object, long championId) {
        try {
            if (championId != -1) {
                this.championId = championId;
            } else {
                this.championId = Long.parseLong(object.get("champion_id").getAsString());
            }

            this.playerId = Long.parseLong(object.get("player_id").getAsString());
            this.playerName = object.get("player_name").getAsString();
            this.playerRanking = Double.parseDouble(object.get("player_ranking").getAsString());

            this.rank = Long.parseLong(object.get("rank").getAsString());
            this.wins = Long.parseLong(object.get("wins").getAsString());
            this.losses = Long.parseLong(object.get("losses").getAsString());

        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
