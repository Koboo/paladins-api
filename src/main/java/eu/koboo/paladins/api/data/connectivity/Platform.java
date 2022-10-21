package eu.koboo.paladins.api.data.connectivity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum Platform {

    XBOX, PS4, PC, SWITCH;

    public static final Platform[] VALUES = Platform.values();

    public static Platform parse(String string) {
        return Platform.valueOf(string.toUpperCase(Locale.ROOT));
    }
}
