package eu.koboo.paladins.api.data.champion;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.data.champion.ability.Ability;
import eu.koboo.paladins.api.data.champion.ability.AbilityType;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
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

    public Collection<Ability> getAllAbilities() {
        return Collections.unmodifiableCollection(abilityMap.values());
    }

    public Ability getAbility(AbilityType abilityType) {
        return abilityMap.get(abilityType);
    }
}
