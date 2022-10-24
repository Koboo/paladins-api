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
public class MatchId {

    long matchId;
    long entryDateTime;
    String region;
    boolean active;
}
