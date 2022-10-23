package eu.koboo.paladins.api.data.match;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
// Source: https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/edit#heading=h.mbb9ngfr6vgg
public enum GameMode {

    /* Custom gamemode queue ids */
    SIEGE_CUSTOM_STONE_KEEP(423, false),
    SIEGE_CUSTOM_BAZAAR(426, false),
    SIEGE_CUSTOM_TIMBER_MILL(430, false),
    SIEGE_CUSTOM_FISH_MARKET(431, false),
    SIEGE_CUSTOM_FROZEN_GUARD(432, false),
    SIEGE_CUSTOM_FROG_ISLE(433, false),
    SIEGE_CUSTOM_JAGUAR_FALLS(438, false),
    SIEGE_CUSTOM_ICE_MINES(439, false),
    SIEGE_CUSTOM_SERPENT_BEACH(440, false),
    SIEGE_CUSTOM_BRIGHTMARSH(458, false),
    SIEGE_CUSTOM_SPLITSTONE_QUARRY(459, false),
    SIEGE_CUSTOM_ASCENSION_PEAK(473, false),
    SIEGE_CUSTOM_WARDERS_GATE(485, false),
    SIEGE_CUSTOM_SHATTERED_DESERT(487, false),

    ONSLAUGHT_CUSTOM_SNOWFALL_JUNCTION(454, false),
    ONSLAUGHT_CUSTOM_PRIMAL_COURT(455, false),
    ONSLAUGHT_CUSTOM_FOREMANS_RISE(462, false),
    ONSLAUGHT_CUSTOM_MAGISTRATES_ARCHIVE(464, false),
    ONSLAUGHT_CUSTOM_MARAUDERS_PORT(483, false),

    KOTH_CUSTOM_MAGISTRATES_ARCHIVE(10200, false),
    KOTH_CUSTOM_SNOWFALL_JUNCTION(10201, false),
    KOTH_CUSTOM_MARAUDERS_PORT(10202, false),
    KOTH_CUSTOM_TRADE_DISTRICT(10203, false),

    TDM_CUSTOM_THRONE(480, false),
    TDM_CUSTOM_TRADE_DISTRICT(468, false),
    TDM_CUSTOM_MAGISTRATES_ARCHIVE(472, false),
    TDM_CUSTOM_FOREMANS_RISE(471, false),
    TDM_CUSTOM_DRAGON_ARENA(484, false),
    TDM_CUSTOM_ABYSS(479, false),

    /* Casual and practice queue ids */
    SIEGE_CASUAL(424, true),
    SIEGE_PRACTICE(425, false),
    SIEGE_TEST_MAPS(445, false),

    ONSLAUGHT_CASUAL(452, true),
    ONSLAUGHT_PRACTICE(453, false),

    TDM_CASUAL(469, true),
    TDM_PRACTICE(470, false),

    /* Competitive queue ids */
    COMPETITIVE_LIVE_CONSOLE(428, true),
    COMPETITIVE_LIVE_PC(486, true),

    /* Other queue ids */
    SHOOTING_RANGE(434, false),
    BASIC_TUTORIAL(444, false);

    public static final GameMode[] VALUES = GameMode.values();

    int queueId;
    boolean recordingStats;

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
