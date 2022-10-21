package eu.koboo.paladins.api.data.champion.skin;

import java.util.Locale;

public enum Rarity {

    DEFAULT,
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY;

    public static final Rarity[] VALUES = Rarity.values();

    public static Rarity parse(String string) {
        if(string.equalsIgnoreCase("")) {
            return DEFAULT;
        }
        return Rarity.valueOf(string.toUpperCase(Locale.ROOT));
    }
}
