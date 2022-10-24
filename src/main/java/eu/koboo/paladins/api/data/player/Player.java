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
}
