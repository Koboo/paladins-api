package eu.koboo.paladins.api.data.player;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.data.items.ItemType;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
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
    String name;
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

    public Player(JsonObject object) {
        try {
            this.activePlayerId = object.get("ActivePlayerId").getAsLong();
            this.playerId = object.get("Id").getAsLong();

            this.avatarId = object.get("AvatarId").getAsLong();
            this.avatarURL = object.get("AvatarURL").getAsString();

            this.accountLevel = object.get("Level").getAsLong();
            this.masteryLevel = object.get("MasteryLevel").getAsLong();
            this.name = object.get("Name").getAsString();
            this.title = object.get("Title").getAsString();
            this.statusMessage = object.get("Personal_Status_Message").getAsString();

            this.privacyFlag = object.get("privacy_flag").getAsString().equalsIgnoreCase("y");
            this.platform = object.get("Platform").getAsString();

            this.region = object.get("Region").getAsString();
            this.createdDatetime = DateFormatter.parseFrom(object.get("Created_Datetime").getAsString());
            this.lastLoginDatetime = DateFormatter.parseFrom(object.get("Last_Login_Datetime").getAsString());
            this.hoursPlayed = object.get("HoursPlayed").getAsLong();
            this.minutesPlayed = object.get("MinutesPlayed").getAsLong();

            this.leaves = object.get("leaves").getAsLong();
            this.losses = object.get("losses").getAsLong();
            this.wins = object.get("wins").getAsLong();
            this.achievements = object.get("Total_Achievements").getAsLong();
            this.xp = object.get("Total_XP").getAsLong();
            this.worshippers = object.get("Total_Worshippers").getAsLong();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
