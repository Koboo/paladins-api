package eu.koboo.paladins.api.data.champion;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.data.champion.ability.Ability;
import eu.koboo.paladins.api.data.champion.ability.AbilityType;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Champion {

    Language language;
    int id;

    String name;
    String englishName;
    String title;

    String pantheon;
    String lore;
    Role role;

    int health;
    int speed;

    boolean onFreeRotation;
    boolean onFreeWeeklyRotation;
    boolean latestChampion;

    String iconURL;

    @Getter(AccessLevel.NONE)
    Map<AbilityType, Ability> abilityMap;

    public Champion(JsonObject object, Language language) {
        try {
            this.language = language;
            this.id = object.get("id").getAsInt();

            this.name = object.get("Name").getAsString();
            this.englishName = object.get("Name_English").getAsString();
            this.title = object.get("Title").getAsString();

            this.pantheon = object.get("Pantheon").getAsString();
            this.lore = object.get("Lore").getAsString();
            this.role = Role.parse(object.get("Roles").getAsString());

            this.health = object.get("Health").getAsInt();
            this.speed = object.get("Speed").getAsInt();

            this.onFreeRotation = object.get("OnFreeRotation").getAsString().equalsIgnoreCase("true");
            this.onFreeWeeklyRotation = object.get("OnFreeWeeklyRotation").getAsString().equalsIgnoreCase("true");
            this.latestChampion = object.get("latestChampion").getAsString().equalsIgnoreCase("y");

            this.iconURL = object.get("ChampionIcon_URL").getAsString();

            // These fields exist in the json result, but they're always empty, so reduce our application resources by not reading them.
            // this.cons = object.get("cons").getAsString();
            // this.pros = object.get("pros").getAsString();
            // this.cardURL = object.get("ChampionCard_URL").getAsString();
            // this.type = object.get("Type").getAsString();

            this.abilityMap = new HashMap<>();
            for (int i = 1; i <= 5; i++) {
                AbilityType abilityType = AbilityType.parse(i);
                String jsonKey = "Ability_" + 1;
                JsonObject abilityObject = object.get(jsonKey).getAsJsonObject();
                Ability ability = new Ability(abilityObject, language, abilityType);
                abilityMap.put(abilityType, ability);
            }
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }

    public Collection<Ability> getAllAbilities() {
        return Collections.unmodifiableCollection(abilityMap.values());
    }

    public Ability getAbility(AbilityType abilityType) {
        return abilityMap.get(abilityType);
    }
}
