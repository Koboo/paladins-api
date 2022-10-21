package eu.koboo.paladins.api.data.items;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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

    public Item(JsonObject object, Language language) {
        try {
            this.language = language;
            this.iconId = object.get("IconId").getAsLong();
            this.itemId = object.get("ItemId").getAsLong();
            this.price = object.get("Price").getAsInt();
            this.deviceName = object.get("DeviceName").getAsString();
            this.description = object.get("Description").getAsString();
            this.shortDescription = object.get("ShortDesc").getAsString();
            this.championId = object.get("champion_id").getAsLong();
            this.iconURL = object.get("itemIcon_URL").getAsString();
            this.itemType = ItemType.parse(object.get("item_type").getAsString());
            this.rechargeSeconds = object.get("recharge_seconds").getAsInt();
            this.talentRewardLevel = object.get("talent_reward_level").getAsInt();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
