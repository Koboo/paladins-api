package eu.koboo.paladins.api.data.player;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.utils.DateFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Player {

    long activePlayerId;
    long playerId;

    long avatarId;
    String avatarURL;

    long accountLevel;
    long masteryLevel;
    String platformName;
    String paladinsName;
    String paladinsGamerTag;
    String title;
    String statusMessage;

    boolean privacyFlag;
    String platform;

    String region;
    long createdDatetime;
    long lastLoginDatetime;
    long hoursPlayed;
    long minutesPlayed;

    long leaves;
    long losses;
    long wins;
    long achievements;
    long xp;
    long worshippers;

    // TODO:
    //  - Other not implemented fields
    //  - Ranked stats
    public Player(JsonObject object) {
        try {
            this.activePlayerId = object.get("ActivePlayerId").getAsLong();
            this.playerId = object.get("Id").getAsLong();

            this.avatarId = object.get("AvatarId").getAsLong();
            this.avatarURL = object.get("AvatarURL").getAsString();

            this.accountLevel = object.get("Level").getAsLong();
            this.masteryLevel = object.get("MasteryLevel").getAsLong();
            this.platformName = object.get("Name").getAsString();
            if(object.has("hz_player_name") && !object.get("hz_player_name").isJsonNull()) {
                this.paladinsName = object.get("hz_player_name").getAsString();
            } else {
                this.paladinsName = platformName;
            }
            if(object.has("hz_gamer_tag") && !object.get("hz_gamer_tag").isJsonNull()) {
                this.paladinsGamerTag = object.get("hz_gamer_tag").getAsString();
            } else {
                this.paladinsGamerTag = null;
            }
            this.title = object.get("Title").getAsString();
            this.statusMessage = object.get("Personal_Status_Message").getAsString();

            this.privacyFlag = object.get("privacy_flag").getAsString().equalsIgnoreCase("y");
            this.platform = object.get("Platform").getAsString();

            this.region = object.get("Region").getAsString();
            this.createdDatetime = DateFormatter.parseFrom(object.get("Created_Datetime").getAsString());
            this.lastLoginDatetime = DateFormatter.parseFrom(object.get("Last_Login_Datetime").getAsString());
            this.hoursPlayed = object.get("HoursPlayed").getAsLong();
            this.minutesPlayed = object.get("MinutesPlayed").getAsLong();

            this.leaves = object.get("Leaves").getAsLong();
            this.losses = object.get("Losses").getAsLong();
            this.wins = object.get("Wins").getAsLong();
            this.achievements = object.get("Total_Achievements").getAsLong();
            this.xp = object.get("Total_XP").getAsLong();
            this.worshippers = object.get("Total_Worshippers").getAsLong();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
