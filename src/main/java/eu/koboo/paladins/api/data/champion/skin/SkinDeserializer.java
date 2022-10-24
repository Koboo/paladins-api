

package eu.koboo.paladins.api.data.champion.skin;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SkinDeserializer implements JsonDeserializer<Skin> {

    @Override
    public Skin deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        Skin skin = new Skin();

        skin.setChampionId(Long.parseLong(object.get("champion_id").getAsString()));
        skin.setChampionName(object.get("champion_name").getAsString());
        skin.setRarity(Rarity.parse(object.get("rarity").getAsString()));

        skin.setSkinId1(object.get("skin_id1").getAsLong());
        skin.setSkinId2(object.get("skin_id2").getAsLong());

        skin.setSkinName(object.get("skin_name").getAsString());
        skin.setSkinNameEnglish(object.get("skin_name_english").getAsString());

        return skin;
    }
}
