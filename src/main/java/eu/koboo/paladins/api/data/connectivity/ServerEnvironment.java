package eu.koboo.paladins.api.data.connectivity;

import java.util.Locale;

public enum ServerEnvironment {

    LIVE, PTS;

    public static ServerEnvironment parse(String string) {
        return ServerEnvironment.valueOf(string.toUpperCase(Locale.ROOT));
    }
}
