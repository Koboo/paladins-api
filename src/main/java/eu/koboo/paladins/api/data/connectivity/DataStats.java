package eu.koboo.paladins.api.data.connectivity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DataStats {

    int sessionTimeLimit;
    int concurrentSessionLimit;
    int totalSessionsActive;
    int dailyRequestLimit;
    int dailySessionLimit;
    int totalRequestToday;
    int totalSessionsToday;
}
