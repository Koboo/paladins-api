package eu.koboo.paladins.api.data.champion;

import java.util.Locale;

public enum Role {

    DAMAGE, FLANKER, FRONT_LINE, SUPPORT;

    public static final Role[] VALUES = Role.values();

    public static Role parse(String string) {
        return Role.valueOf(string.toUpperCase(Locale.ROOT)
                .replaceAll(" ", "_")
                .replaceFirst("PALADINS_", ""));
    }
}
