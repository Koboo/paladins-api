package eu.koboo.paladins.api.utils;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.request.APIRequest;

public class SessionUtils {

    public static String parseSessionId(APIRequest request) {
        JsonObject object = request.asJsonObject();
        if(object == null) {
            return null;
        }
        if(!object.has("ret_msg")) {
            return null;
        }
        if(!object.get("ret_msg").getAsString().equals("Approved")) {
            return null;
        }
        if(!object.has("session_id")) {
            return null;
        }
        return object.get("session_id").getAsString();
    }

    public static boolean isValidSession(APIRequest request) {
        String result = request.asString();
        if(result == null || result.isBlank() || result.isEmpty()) {
            return false;
        }
        result = result.replaceAll("\"", "");
        return !result.startsWith("Invalid session id.");
    }
}
