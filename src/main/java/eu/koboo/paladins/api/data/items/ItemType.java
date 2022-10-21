package eu.koboo.paladins.api.data.items;

public enum ItemType {

    CHAMPION_CARD,
    TALENT,
    TALENT_DEFAULT,
    BUY_ABLE_DEFENSE,
    BUY_ABLE_UTILITY,
    BUY_ABLE_HEALING,
    BUY_ABLE_DAMAGE,
    NONE
    ;

    public static final ItemType[] VALUES = ItemType.values();

    public static ItemType parse(String string) {
        if(string.equalsIgnoreCase("Inventory Vendor - Champion Cards")) {
            return CHAMPION_CARD;
        }
        if(string.equalsIgnoreCase("Inventory Vendor - Talents")) {
            return TALENT;
        }
        if(string.equalsIgnoreCase("Inventory Vendor - Talents Default")) {
            return TALENT_DEFAULT;
        }
        if(string.equalsIgnoreCase("Burn Card Damage Vendor")) {
            return BUY_ABLE_DAMAGE;
        }
        if(string.equalsIgnoreCase("Burn Card Utility Vendor")) {
            return BUY_ABLE_UTILITY;
        }
        if(string.equalsIgnoreCase("Burn Card Healing Vendor")) {
            return BUY_ABLE_HEALING;
        }
        if(string.equalsIgnoreCase("Burn Card Defense Vendor")) {
            return BUY_ABLE_DEFENSE;
        }
        return CHAMPION_CARD;
    }
}
