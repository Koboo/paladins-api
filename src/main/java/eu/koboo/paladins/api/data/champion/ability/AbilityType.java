package eu.koboo.paladins.api.data.champion.ability;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@RequiredArgsConstructor
public enum AbilityType {

    MAIN(1),
    OFF_MAIN(2),
    FIRST_EXTRA(3),
    SECOND_EXTRA(4),
    ULTIMATE(5);

    public static final AbilityType[] VALUES = AbilityType.values();

    int number;

    public static AbilityType parse(int abilityNumber) {
        for (AbilityType value : VALUES) {
            if(value.getNumber() != abilityNumber) {
                continue;
            }
            return value;
        }
        return null;
    }
}
