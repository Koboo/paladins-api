package eu.koboo.paladins.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum Language {

    ENGLISH(1),
    GERMAN(2),
    FRENCH(3),
    SPANISH(7),
    SPANISH_LATIN_AMERICA(9),
    PORTUGUESE(10),
    RUSSIAN(11),
    POLISH(12),
    TURKISH(13);

    int value;
}
