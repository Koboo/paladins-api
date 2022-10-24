package eu.koboo.paladins.api.data.champion.leaderboard;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoardRank {

    long championId;

    long playerId;
    String playerName;
    double playerRanking;

    long rank;
    long wins;
    long losses;
}
