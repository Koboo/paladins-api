package eu.koboo.paladins.api.data.connectivity;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DataStatsDeserializer implements JsonDeserializer<DataStats> {

    @Override
    public DataStats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        DataStats dataStats = new DataStats();

        dataStats.setSessionTimeLimit(object.get("Session_Time_Limit").getAsInt());
        dataStats.setConcurrentSessionLimit(object.get("Concurrent_Sessions").getAsInt());
        dataStats.setTotalSessionsActive(object.get("Active_Sessions").getAsInt());
        dataStats.setDailyRequestLimit(object.get("Request_Limit_Daily").getAsInt());
        dataStats.setDailySessionLimit(object.get("Session_Cap").getAsInt());
        dataStats.setTotalRequestToday(object.get("Total_Requests_Today").getAsInt());
        dataStats.setTotalSessionsToday(object.get("Total_Sessions_Today").getAsInt());

        return dataStats;
    }
}
