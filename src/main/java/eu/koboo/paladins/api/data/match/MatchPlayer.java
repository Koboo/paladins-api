package eu.koboo.paladins.api.data.match;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.utils.DateFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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

    public MatchPlayer(JsonObject object) {
        try {
            this.playerId = Long.parseLong(object.get("ActivePlayerId").getAsString());
            this.championId = object.get("ChampionId").getAsLong();
            this.matchId = object.get("Match").getAsLong();
            this.queueId = object.get("match_queue_id").getAsLong();
            this.matchDuration = object.get("Match").getAsLong();
            this.matchTimeStamp = DateFormatter.parseFrom(object.get("Entry_Datetime").getAsString());

            this.winning = object.get("Win_Status").getAsString().equalsIgnoreCase("Winner");

            this.killingSpree = object.get("Killing_Spree").getAsLong();

            this.killsPlayer = object.get("Kills_Player").getAsLong();
            this.killsBot = object.get("Kills_Bot").getAsLong();

            this.deaths = object.get("Deaths").getAsLong();
            this.assists = object.get("Assists").getAsLong();

            this.damageDoneInHand = object.get("Damage_Done_In_Hand").getAsLong();
            this.damageDoneMagical= object.get("Damage_Done_Magical").getAsLong();
            this.damageDonePhysical = object.get("Damage_Done_Physical").getAsLong();

            this.damageBot = object.get("Damage_Bot").getAsLong();
            this.damagePlayer = object.get("Damage_Player").getAsLong();

            this.damageMitigated = object.get("Damage_Mitigated").getAsLong();
            this.damageTaken = object.get("Damage_Taken").getAsLong();
            this.damageTakenMagical = object.get("Damage_Taken_Magical").getAsLong();
            this.damageTakenPhysical = object.get("Damage_Taken_Physical").getAsLong();

            this.healing = object.get("Healing").getAsLong();
            this.healingBot = object.get("Healing_Bot").getAsLong();
            this.healingSelf = object.get("Healing_Player_Self").getAsLong();

            this.goldEarned = object.get("Gold_Earned").getAsLong();
            this.goldPerMinute = object.get("Gold_Per_Minute").getAsLong();

            this.timeLived = object.get("Time_In_Match_Seconds").getAsLong();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
