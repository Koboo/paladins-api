package eu.koboo.paladins.api.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public enum Minutes {

    ZERO("00"),
    TEN("10"),
    TWENTY("20"),
    THIRTY("30"),
    FORTY("40"),
    FIFTY("50");

    String value;
}
