package eu.koboo.paladins.api.data.player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
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
}
