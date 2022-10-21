package eu.koboo.paladins.api.data.champion.skin;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Skin {

    long championId;
    String championName;
    Rarity rarity;

    long skinId1;
    long skinId2;

    Language language;
    String skinName;
    String skinNameEnglish;

    public Skin(JsonObject object, long championId, Language language) {
        try {
            if(championId != -1) {
                this.championId = championId;
            } else {
                this.championId = Long.parseLong(object.get("champion_id").getAsString());
            }
            this.championName = object.get("champion_name").getAsString();
            this.rarity = Rarity.parse(object.get("rarity").getAsString());

            this.skinId1 = object.get("skin_id1").getAsLong();
            this.skinId2 = object.get("skin_id2").getAsLong();

            this.language = language;
            this.skinName = object.get("skin_name").getAsString();
            this.skinNameEnglish = object.get("skin_name_english").getAsString();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
