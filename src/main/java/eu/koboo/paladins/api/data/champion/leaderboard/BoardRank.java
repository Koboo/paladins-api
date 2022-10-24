package eu.koboo.paladins.api.data.champion.leaderboard;

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
