import eu.koboo.paladins.api.APIConfig;
import eu.koboo.paladins.api.PaladinsAPI;
import eu.koboo.paladins.api.data.champion.Champion;
import eu.koboo.paladins.api.data.champion.leaderboard.BoardRank;
import eu.koboo.paladins.api.data.champion.skin.Skin;
import eu.koboo.paladins.api.data.connectivity.DataStats;
import eu.koboo.paladins.api.data.connectivity.ServerStatus;
import eu.koboo.paladins.api.data.items.Item;
import eu.koboo.paladins.api.data.match.GameMode;
import eu.koboo.paladins.api.data.match.MatchId;
import eu.koboo.paladins.api.data.match.MatchPlayer;
import eu.koboo.paladins.api.data.player.Player;
import eu.koboo.paladins.api.data.player.PlayerChampion;
import eu.koboo.paladins.api.request.Hours;
import eu.koboo.paladins.api.request.Language;
import eu.koboo.paladins.api.utils.DateFormatter;
import eu.koboo.paladins.api.utils.Validator;
import eu.koboo.propconf.PropertyConfig;
import eu.koboo.propconf.key.StringKey;

import java.util.List;

public class Test {

    private static final StringKey DEV_ID = new StringKey("devId", "XXXX");
    private static final StringKey AUTH_KEY = new StringKey("authKey", "XXXX");
    private static final StringKey SESSION_ID = new StringKey("sessionId", "XXXX");

    public static void main(String[] args) {
        boolean[] which = new boolean[]{
                false, // ping
                false, // data stats
                false, // server status list
                false, // patch info
                false, // get all champions
                false, // get champion leaderboard
                false, // get champion skins
                false, // get items
                false, // get player
                true, // get match
        };

        System.out.println("=====> Reading properties..");
        String configFile = "access.properties";
        PropertyConfig config = PropertyConfig.fromFile(configFile);

        String devId = config.getOr(DEV_ID);
        Validator.notNull(devId, "DevId is null");

        String authKey = config.getOr(AUTH_KEY);
        Validator.notNull(devId, "AuthKey is null");

        System.out.println("=====> Creating API wrapper..");
        PaladinsAPI api = APIConfig.builder(devId, authKey)
                .requestTimeout(10)
                .sessionId(config.getOr(SESSION_ID))
                .refreshSessionOnStartup(true)
                .onNewSessionId(newSessionId -> {
                    config.set(SESSION_ID, newSessionId);
                    config.save(configFile);
                })
                .create();

        //long ioChampionId = 2517;
        long ioChampionId = 2431;

        if (which[0]) {
            System.out.println("=====> Pinging API..");
            System.out.println("API is " + (api.isAPIReachable() ? "reachable" : "not reachable") + ".");
        }

        if (which[1]) {
            System.out.println("=====> Getting API-Stats..");
            DataStats dataStats = api.getDataStats();
            System.out.println("Max Session TimeLimit: " + dataStats.getSessionTimeLimit());
            System.out.println("Current Sessions: " + dataStats.getTotalSessionsActive() + "/" + dataStats.getConcurrentSessionLimit());
            System.out.println("Daily Requests: " + dataStats.getTotalRequestToday() + "/" + dataStats.getDailyRequestLimit());
            System.out.println("Daily Sessions: " + dataStats.getTotalSessionsToday() + "/" + dataStats.getDailySessionLimit());
        }

        if (which[2]) {
            System.out.println("=====> Getting ServerStatus..");
            List<ServerStatus> serverStatusList = api.getServerStatusList();
            for (ServerStatus serverStatus : serverStatusList) {
                System.out.println("Server: " + serverStatus.getPlatform() + " | " + serverStatus.getStatusText());
                System.out.println("  - Version: " + serverStatus.getVersion());
                System.out.println("  - Entry-Timestamp: " + serverStatus.getEntryTimeStamp());
                System.out.println("  - Environment: " + serverStatus.getEnvironment().name());
            }
        }

        if (which[3]) {
            System.out.println("=====> Getting PatchInfo..");
            String patchInfo = api.getPatchInfo();
            if (patchInfo == null) {
                patchInfo = "unknown";
            }
            System.out.println("Current PatchInfo: " + patchInfo);
        }

        if (which[4]) {
            System.out.println("=====> Getting Champions..");
            List<Champion> championList = api.getAllChampions(Language.ENGLISH);
            System.out.println("Champions: " + championList.size());
        }

        if (which[5]) {
            System.out.println("=====> Getting ChampionLeaderboard (" + ioChampionId + ")..");
            List<BoardRank> boardRankList = api.getChampionLeaderboardConsole(ioChampionId);
            System.out.println("BoardRanks: " + boardRankList.size());
            System.out.println("First Place: " + boardRankList.get(0).getPlayerName());
        }

        if (which[6]) {
            System.out.println("=====> Getting ChampionSkins..");
            List<Skin> skinList = api.getChampionSkins(Language.ENGLISH, -1);
            System.out.println("Skins: " + skinList.size());
        }

        if (which[7]) {
            System.out.println("=====> Getting Items..");
            List<Item> itemList = api.getItems(Language.GERMAN);
            System.out.println("Items: " + itemList.size());
        }

        if (which[8]) {
            System.out.println("=====> Getting Player..");
            Player player = api.getPlayer("Muffeltier");
            System.out.println("Player: " + player.getPlayerId() + " | " + player.getPaladinsName() + " | " + player.getPlatformName());
            List<PlayerChampion> list = api.getPlayerChampions(player.getPlayerId());
            System.out.println("Played Champions: " + list.size());
        }

        if (which[9]) {
            GameMode[] gameModes = {
                    GameMode.SIEGE_CASUAL, GameMode.ONSLAUGHT_CASUAL, GameMode.TDM_CASUAL, GameMode.COMPETITIVE_LIVE_PC,
                    GameMode.COMPETITIVE_LIVE_CONSOLE
            };
            for (GameMode gameMode : gameModes) {
                List<MatchId> matchIdList = api.getMatchIdByQueue(gameMode.getQueueId(), System.currentTimeMillis(), Hours.DAY, null);

                System.out.println(gameMode.name() + ": " + matchIdList.size());
                List<MatchPlayer> matchPlayerList = api.getMatchDetails(matchIdList.get(0).getMatchId());
                for (MatchPlayer matchPlayer : matchPlayerList) {
                    System.out.println("> " + matchPlayer.getPlayerId());
                    System.out.println(matchPlayer.getMatchDuration());
                    System.out.println(DateFormatter.formatPathDate(matchPlayer.getMatchTimeStamp()));
                }
            }
        }
    }
}
