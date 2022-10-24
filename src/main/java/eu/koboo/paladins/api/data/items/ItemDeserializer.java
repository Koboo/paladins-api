
package eu.koboo.paladins.api.data.items;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        Item item = new Item();

        item.setIconId(object.get("IconId").getAsLong());
        item.setItemId(object.get("ItemId").getAsLong());
        item.setPrice(object.get("Price").getAsInt());
        item.setDeviceName(object.get("DeviceName").getAsString());
        item.setDescription(object.get("Description").getAsString());
        item.setShortDescription(object.get("ShortDesc").getAsString());
        item.setChampionId(object.get("champion_id").getAsLong());
        item.setIconURL(object.get("itemIcon_URL").getAsString());
        item.setItemType(ItemType.parse(object.get("item_type").getAsString()));
        item.setRechargeSeconds(object.get("recharge_seconds").getAsInt());
        item.setTalentRewardLevel(object.get("talent_reward_level").getAsInt());

        return item;
    }
}
