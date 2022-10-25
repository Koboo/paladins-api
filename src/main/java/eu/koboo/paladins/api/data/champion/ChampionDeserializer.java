package eu.koboo.paladins.api.data.champion;

import com.google.gson.*;
import eu.koboo.paladins.api.data.champion.ability.Ability;
import eu.koboo.paladins.api.data.champion.ability.AbilityType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ChampionDeserializer implements JsonDeserializer<Champion> {

    @Override
    public Champion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        Champion champion = new Champion();

        champion.setId(object.get("id").getAsInt());

        champion.setName(object.get("Name").getAsString());
        champion.setEnglishName(object.get("Name_English").getAsString());
        champion.setTitle(object.get("Title").getAsString());

        champion.setPantheon(object.get("Pantheon").getAsString());
        champion.setLore(object.get("Lore").getAsString());
        champion.setRole(Role.parse(object.get("Roles").getAsString()));

        champion.setHealth(object.get("Health").getAsInt());
        champion.setSpeed(object.get("Speed").getAsInt());

        champion.setOnFreeRotation(object.get("OnFreeRotation").getAsString().equalsIgnoreCase("true"));
        champion.setOnFreeWeeklyRotation(object.get("OnFreeWeeklyRotation").getAsString().equalsIgnoreCase("true"));
        champion.setLatestChampion(object.get("latestChampion").getAsString().equalsIgnoreCase("y"));

        champion.setIconURL(object.get("ChampionIcon_URL").getAsString());

        // These fields exist in the json result, but they're always empty, so reduce our application resources by not reading them.
        // this.cons = object.get("cons").getAsString();
        // this.pros = object.get("pros").getAsString();
        // this.cardURL = object.get("ChampionCard_URL").getAsString();
        // this.type = object.get("Type").getAsString();

        Map<AbilityType, Ability> abilityMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            AbilityType abilityType = AbilityType.parse(i);
            String jsonKey = "Ability_" + 1;
            JsonObject abilityObject = object.get(jsonKey).getAsJsonObject();
            Ability ability = context.deserialize(abilityObject, Ability.class);
            ability.setAbilityType(abilityType);
            abilityMap.put(abilityType, ability);
        }
        champion.setAbilityMap(abilityMap);
        return champion;
    }
}
