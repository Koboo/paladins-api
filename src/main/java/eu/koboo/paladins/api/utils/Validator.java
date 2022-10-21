package eu.koboo.paladins.api.utils;

public class Validator {

    public static void notNull(Object object) {
        notNull(object, null);
    }

    public static void notNull(Object object, String appendText) {
        if(object == null) {
            throw new NullPointerException("An object of the type " + object.getClass().getName() + " is null." + (appendText == null ? "" : " " + appendText));
        }
    }

    public static boolean isEmpty(String result) {
        if(result != null) {
            result = result.replaceAll("\"", "");
        }
        return result == null || result.isBlank() || result.isEmpty() || result.equalsIgnoreCase("");
    }

    public static void apiMethod(String sessionId, String methodName) {
        Validator.notNull(sessionId, "SessionId is required for " + methodName + " request.");
    }
}
