
package eu.koboo.paladins.api.data.player;

import com.google.gson.*;
import eu.koboo.paladins.api.utils.DateFormatter;

import java.lang.reflect.Type;

public class PlayerDeserializer implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        Player player = new Player();

        player.setActivePlayerId(object.get("ActivePlayerId").getAsLong());
        player.setPlayerId(object.get("Id").getAsLong());

        player.setAvatarId(object.get("AvatarId").getAsLong());
        player.setAvatarURL(object.get("AvatarURL").getAsString());

        player.setAccountLevel(object.get("Level").getAsLong());
        player.setMasteryLevel(object.get("MasteryLevel").getAsLong());
        player.setPlatformName(object.get("Name").getAsString());
        if(object.has("hz_player_name") && !object.get("hz_player_name").isJsonNull()) {
            player.setPaladinsName(object.get("hz_player_name").getAsString());
        } else {
            player.setPaladinsName(player.getPlatformName());
        }
        if(object.has("hz_gamer_tag") && !object.get("hz_gamer_tag").isJsonNull()) {
            player.setPaladinsGamerTag(object.get("hz_gamer_tag").getAsString());
        } else {
            player.setPaladinsGamerTag(null);
        }
        player.setTitle(object.get("Title").getAsString());
        player.setStatusMessage(object.get("Personal_Status_Message").getAsString());

        player.setPrivacyFlag(object.get("privacy_flag").getAsString().equalsIgnoreCase("y"));
        player.setPlatform(object.get("Platform").getAsString());

        player.setRegion(object.get("Region").getAsString());
        player.setCreatedDatetime(DateFormatter.parseFrom(object.get("Created_Datetime").getAsString()));
        player.setLastLoginDatetime(DateFormatter.parseFrom(object.get("Last_Login_Datetime").getAsString()));
        player.setHoursPlayed(object.get("HoursPlayed").getAsLong());
        player.setMinutesPlayed(object.get("MinutesPlayed").getAsLong());

        player.setLeaves(object.get("Leaves").getAsLong());
        player.setLosses(object.get("Losses").getAsLong());
        player.setWins(object.get("Wins").getAsLong());
        player.setAchievements(object.get("Total_Achievements").getAsLong());
        player.setXp(object.get("Total_XP").getAsLong());
        player.setWorshippers(object.get("Total_Worshippers").getAsLong());

        return player;
    }
}
