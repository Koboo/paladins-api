package eu.koboo.paladins.api.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public enum GameMode {

    /* Custom gamemode queue ids */
    SIEGE_CUSTOM_STONE_KEEP(423),
    SIEGE_CUSTOM_BAZAAR(426),
    SIEGE_CUSTOM_TIMBER_MILL(430),
    SIEGE_CUSTOM_FISH_MARKET(431),
    SIEGE_CUSTOM_FROZEN_GUARD(432),
    SIEGE_CUSTOM_FROG_ISLE(433),
    SIEGE_CUSTOM_JAGUAR_FALLS(438),
    SIEGE_CUSTOM_ICE_MINES(439),
    SIEGE_CUSTOM_SERPENT_BEACH(440),
    SIEGE_CUSTOM_BRIGHTMARSH(458),
    SIEGE_CUSTOM_SPLITSTONE_QUARRY(459),
    SIEGE_CUSTOM_ASCENSION_PEAK(473),
    SIEGE_CUSTOM_WARDERS_GATE(485),
    SIEGE_CUSTOM_SHATTERED_DESERT(487),

    ONSLAUGHT_CUSTOM_SNOWFALL_JUNCTION(454),
    ONSLAUGHT_CUSTOM_PRIMAL_COURT(455),
    ONSLAUGHT_CUSTOM_FOREMANS_RISE(462),
    ONSLAUGHT_CUSTOM_MAGISTRATES_ARCHIVE(464),
    ONSLAUGHT_CUSTOM_MARAUDERS_PORT(483),

    KOTH_CUSTOM_MAGISTRATES_ARCHIVE(10200),
    KOTH_CUSTOM_SNOWFALL_JUNCTION(10201),
    KOTH_CUSTOM_MARAUDERS_PORT(10202),
    KOTH_CUSTOM_TRADE_DISTRICT(10203),

    TDM_CUSTOM_TRADE_DISTRICT(468),
    TDM_CUSTOM_FOREMANS_RISE(471),
    TDM_CUSTOM_MAGISTRATES_ARCHIVE(472),
    TDM_CUSTOM_ABYSS(479),
    TDM_CUSTOM_THRONE(480),
    TDM_CUSTOM_DRAGON_ARENA(484),

    /* Casual and practice queue ids */
    SIEGE_CASUAL(424),
    SIEGE_PRACTICE(425),
    SIEGE_TEST_MAPS(445),

    ONSLAUGHT_CASUAL(452),
    ONSLAUGHT_PRACTICE(453),

    TDM_CASUAL(469),
    TDM_PRACTICE(470),

    /* Competitive queue ids */
    COMPETITIVE_LIVE_CONSOLE(428),
    COMPETITIVE_LIVE_PC(486),

    /* Other queue ids */
    SHOOTING_RANGE(434),
    BASIC_TUTORIAL(444);

    public static final GameMode[] VALUES = GameMode.values();

    int queueId;

    public static GameMode parse(int queueId) {
        for (GameMode gameMode : VALUES) {
            if(gameMode.getQueueId() != queueId) {
                continue;
            }
            return gameMode;
        }
        return null;
    }
}
