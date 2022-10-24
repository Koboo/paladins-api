package eu.koboo.paladins.api.data.items;

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
public class Item {

    Language language;
    long iconId;
    long itemId;
    int price;
    String deviceName;
    String description;
    String shortDescription;
    long championId;
    String iconURL;
    ItemType itemType;
    int rechargeSeconds;
    int talentRewardLevel;
}
