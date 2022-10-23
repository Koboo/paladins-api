package eu.koboo.paladins.api.data.champion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@RequiredArgsConstructor
public enum ChampionId {

    BETTY_LA_BOMBA(2550, Role.DAMAGE),
    BOMB_KING(2281, Role.DAMAGE),
    CASSIE(2092, Role.DAMAGE),
    DREDGE(2495, Role.DAMAGE),
    DROGOZ(2277, Role.DAMAGE),
    IMANI(2509, Role.DAMAGE),
    KINESSA(2249, Role.DAMAGE),
    LIAN(2417, Role.DAMAGE),
    OCTAVIA(2540, Role.DAMAGE),
    SAATI(2543, Role.DAMAGE),
    SHA_LIN(2307, Role.DAMAGE),
    STRIX(2438, Role.DAMAGE),
    TIBERIUS(2529, Role.DAMAGE),
    TYRA(2314, Role.DAMAGE),
    VIKTOR(2285, Role.DAMAGE),
    VIVIAN(2480, Role.DAMAGE),
    WILLO(2393, Role.DAMAGE),
    ANDROXUS(2205, Role.FLANKER),
    BUCK(2147, Role.FLANKER),
    CASPIAN(2554, Role.FLANKER),
    EVIE(2094, Role.FLANKER),
    KASUMI(2555, Role.FLANKER),
    KOGA(2493, Role.FLANKER),
    LEX(2362, Role.FLANKER),
    MAEVE(2338, Role.FLANKER),
    MOJI(2481, Role.FLANKER),
    SKYE(2057, Role.FLANKER),
    TALUS(2472, Role.FLANKER),
    VATU(2541, Role.FLANKER),
    VII(2549, Role.FLANKER),
    VORA(2536, Role.FLANKER),
    ZHIN(2420, Role.FLANKER),
    ASH(2404, Role.FRONT_LINE),
    ATLAS(2512, Role.FRONT_LINE),
    AZAAN(2548, Role.FRONT_LINE),
    BARIK(2073, Role.FRONT_LINE),
    FERNANDO(2071, Role.FRONT_LINE),
    INARA(2348, Role.FRONT_LINE),
    KHAN(2479, Role.FRONT_LINE),
    MAKOA(2288, Role.FRONT_LINE),
    RAUM(2528, Role.FRONT_LINE),
    RUCKUS(2149, Role.FRONT_LINE),
    TERMINUS(2477, Role.FRONT_LINE),
    TORVALD(2322, Role.FRONT_LINE),
    YAGORATH(2538, Role.FRONT_LINE),
    CORVUS(2533, Role.SUPPORT),
    FURIA(2491, Role.SUPPORT),
    GROHK(2093, Role.SUPPORT),
    GROVER(2254, Role.SUPPORT),
    IO(2517, Role.SUPPORT),
    JENOS(2431, Role.SUPPORT),
    LILLITH(2551, Role.SUPPORT),
    MAL_DAMBA(2303, Role.SUPPORT),
    PIP(2056, Role.SUPPORT),
    REI(2542, Role.SUPPORT),
    SERIS(2372, Role.SUPPORT),
    YING(2267, Role.SUPPORT),
    ;

    public static final ChampionId[] VALUES = ChampionId.values();

    long id;
    Role role;

    public static ChampionId parse(int championId) {
        for (ChampionId value : VALUES) {
            if(value.getId() != championId) {
                continue;
            }
            return value;
        }
        return null;
    }
}
