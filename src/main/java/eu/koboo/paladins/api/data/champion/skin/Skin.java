package eu.koboo.paladins.api.data.champion.skin;

import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Skin {

    long championId;
    String championName;
    Rarity rarity;

    long skinId1;
    long skinId2;

    Language language;
    String skinName;
    String skinNameEnglish;
}
