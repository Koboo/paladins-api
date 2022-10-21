package eu.koboo.paladins.api.data.connectivity;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataStats {

    int sessionTimeLimit;
    int concurrentSessionLimit;
    int totalSessionsActive;
    int dailyRequestLimit;
    int dailySessionLimit;
    int totalRequestToday;
    int totalSessionsToday;

    public DataStats(JsonObject object) {
        try {
            sessionTimeLimit = object.get("Session_Time_Limit").getAsInt();
            concurrentSessionLimit = object.get("Concurrent_Sessions").getAsInt();
            totalSessionsActive = object.get("Active_Sessions").getAsInt();
            dailyRequestLimit = object.get("Request_Limit_Daily").getAsInt();
            dailySessionLimit = object.get("Session_Cap").getAsInt();
            totalRequestToday = object.get("Total_Requests_Today").getAsInt();
            totalSessionsToday = object.get("Total_Sessions_Today").getAsInt();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
