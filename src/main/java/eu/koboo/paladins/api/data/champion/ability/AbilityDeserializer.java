
package eu.koboo.paladins.api.data.champion.ability;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Locale;

public class AbilityDeserializer implements JsonDeserializer<Ability> {

    @Override
    public Ability deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        Ability ability = new Ability();

        //ability.setlanguage(language);
        //ability.setabilityType(abilityType);
        ability.setId(object.get("Id").getAsInt());

        ability.setName(object.get("Summary").getAsString());
        ability.setDescription(object.get("Description").getAsString());
        ability.setDamageType(DamageType.valueOf(object.get("damageType").getAsString().toUpperCase(Locale.ROOT)));

        ability.setRechargeSeconds(object.get("rechargeSeconds").getAsInt());

        ability.setIconUrl(object.get("URL").getAsString());

        return ability;
    }
}
