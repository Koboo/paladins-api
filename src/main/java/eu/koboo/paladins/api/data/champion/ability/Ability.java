package eu.koboo.paladins.api.data.champion.ability;

import com.google.gson.JsonObject;
import eu.koboo.paladins.api.exceptions.DataParseException;
import eu.koboo.paladins.api.request.Language;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Ability {

    Language language;
    AbilityType abilityType;
    int id;

    String name;
    String description;
    DamageType damageType;

    int rechargeSeconds;

    String iconUrl;

    public Ability(JsonObject object, Language language, AbilityType abilityType) {
        try {
            this.language = language;
            this.abilityType = abilityType;
            this.id = object.get("Id").getAsInt();

            this.name = object.get("Summary").getAsString();
            this.description = object.get("Description").getAsString();
            this.damageType = DamageType.valueOf(object.get("damageType").getAsString().toUpperCase(Locale.ROOT));

            this.rechargeSeconds = object.get("rechargeSeconds").getAsInt();

            this.iconUrl = object.get("URL").getAsString();
        } catch (Exception e) {
            throw new DataParseException(this.getClass(), e);
        }
    }
}
