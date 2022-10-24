package eu.koboo.paladins.api.data.match;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchPlayer {

    long playerId;
    long championId;
    long matchId;
    long queueId;
    long matchDuration;
    long matchTimeStamp;

    boolean winning;

    long killingSpree;
    long killsBot;
    long killsPlayer;

    long deaths;
    long assists;

    long damageDoneInHand;
    long damageDoneMagical;
    long damageDonePhysical;

    long damageBot;
    long damagePlayer;

    long damageMitigated;
    long damageTaken;
    long damageTakenMagical;
    long damageTakenPhysical;

    long healing;
    long healingBot;
    long healingSelf;

    long goldEarned;
    long goldPerMinute;

    long timeLived;
}
