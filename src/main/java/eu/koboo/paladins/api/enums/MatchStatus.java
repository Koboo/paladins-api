package eu.koboo.paladins.api.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@AllArgsConstructor
public enum MatchStatus {

    OFFLINE(0),
    IN_LOBBY(1),
    CHAMPION_SELECTION(2),
    IN_GAME(3),
    ONLINE(4);

    int statusCode;

    public static MatchStatus getAsValue(int statusCode) {
        for (MatchStatus matchStatus : MatchStatus.values()) {
            if (matchStatus.getStatusCode() != statusCode) {
                continue;
            }
            return matchStatus;
        }
        return null;
    }
}
