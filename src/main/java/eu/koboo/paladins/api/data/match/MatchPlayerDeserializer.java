package eu.koboo.paladins.api.data.match;

import com.google.gson.*;
import eu.koboo.paladins.api.utils.DateFormatter;

import java.lang.reflect.Type;

public class MatchPlayerDeserializer implements JsonDeserializer<MatchPlayer> {

    @Override
    public MatchPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        MatchPlayer matchPlayer = new MatchPlayer();

        matchPlayer.setPlayerId(Long.parseLong(object.get("ActivePlayerId").getAsString()));
        matchPlayer.setChampionId(object.get("ChampionId").getAsLong());
        matchPlayer.setMatchId(object.get("Match").getAsLong());
        matchPlayer.setQueueId(object.get("match_queue_id").getAsLong());
        matchPlayer.setMatchDuration(object.get("Match").getAsLong());
        matchPlayer.setMatchTimeStamp(DateFormatter.parseFrom(object.get("Entry_Datetime").getAsString()));

        matchPlayer.setWinning(object.get("Win_Status").getAsString().equalsIgnoreCase("Winner"));

        matchPlayer.setKillingSpree(object.get("Killing_Spree").getAsLong());

        matchPlayer.setKillsPlayer(object.get("Kills_Player").getAsLong());
        matchPlayer.setKillsBot(object.get("Kills_Bot").getAsLong());

        matchPlayer.setDeaths(object.get("Deaths").getAsLong());
        matchPlayer.setAssists(object.get("Assists").getAsLong());

        matchPlayer.setDamageDoneInHand(object.get("Damage_Done_In_Hand").getAsLong());
        matchPlayer.setDamageDoneMagical(object.get("Damage_Done_Magical").getAsLong());
        matchPlayer.setDamageDonePhysical(object.get("Damage_Done_Physical").getAsLong());

        matchPlayer.setDamageBot(object.get("Damage_Bot").getAsLong());
        matchPlayer.setDamagePlayer(object.get("Damage_Player").getAsLong());

        matchPlayer.setDamageMitigated(object.get("Damage_Mitigated").getAsLong());
        matchPlayer.setDamageTaken(object.get("Damage_Taken").getAsLong());
        matchPlayer.setDamageTakenMagical(object.get("Damage_Taken_Magical").getAsLong());
        matchPlayer.setDamageTakenPhysical(object.get("Damage_Taken_Physical").getAsLong());

        matchPlayer.setHealing(object.get("Healing").getAsLong());
        matchPlayer.setHealingBot(object.get("Healing_Bot").getAsLong());
        matchPlayer.setHealingSelf(object.get("Healing_Player_Self").getAsLong());

        matchPlayer.setGoldEarned(object.get("Gold_Earned").getAsLong());
        matchPlayer.setGoldPerMinute(object.get("Gold_Per_Minute").getAsLong());

        matchPlayer.setTimeLived(object.get("Time_In_Match_Seconds").getAsLong());

        return matchPlayer;
    }
}
