package eu.koboo.paladins.api;

import com.google.gson.*;
import eu.koboo.paladins.api.data.champion.Champion;
import eu.koboo.paladins.api.data.champion.ChampionDeserializer;
import eu.koboo.paladins.api.data.champion.ChampionId;
import eu.koboo.paladins.api.data.champion.ability.Ability;
import eu.koboo.paladins.api.data.champion.ability.AbilityDeserializer;
import eu.koboo.paladins.api.data.champion.leaderboard.BoardRank;
import eu.koboo.paladins.api.data.champion.leaderboard.BoardRankDeserializer;
import eu.koboo.paladins.api.data.champion.skin.Skin;
import eu.koboo.paladins.api.data.champion.skin.SkinDeserializer;
import eu.koboo.paladins.api.data.connectivity.DataStats;
import eu.koboo.paladins.api.data.connectivity.DataStatsDeserializer;
import eu.koboo.paladins.api.data.connectivity.ServerStatus;
import eu.koboo.paladins.api.data.connectivity.ServerStatusDeserializer;
import eu.koboo.paladins.api.data.items.Item;
import eu.koboo.paladins.api.data.items.ItemDeserializer;
import eu.koboo.paladins.api.data.match.*;
import eu.koboo.paladins.api.data.player.Player;
import eu.koboo.paladins.api.data.player.PlayerChampion;
import eu.koboo.paladins.api.data.player.PlayerChampionDeserializer;
import eu.koboo.paladins.api.data.player.PlayerDeserializer;
import eu.koboo.paladins.api.request.*;
import eu.koboo.paladins.api.utils.SessionUtils;
import eu.koboo.paladins.api.utils.Validator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaladinsAPI {

    APIConfig config;
    HttpClient client;
    Gson gson;

    @NonFinal
    String currentSessionId;
    @NonFinal
    long createdSessionTimeStamp;

    protected PaladinsAPI(APIConfig config) {
        // Configuration of the API
        this.config = config;

        // HttpClient to make requests through the APIRequest object
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.of(config.getRequestTimeoutSeconds(), ChronoUnit.SECONDS))
                .build();

        // Parse result into java objects
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Item.class, new ItemDeserializer())
                .registerTypeAdapter(MatchId.class, new MatchIdDeserializer())
                .registerTypeAdapter(MatchPlayer.class, new MatchPlayerDeserializer())
                .registerTypeAdapter(Player.class, new PlayerDeserializer())
                .registerTypeAdapter(PlayerChampion.class, new PlayerChampionDeserializer())
                .registerTypeAdapter(ServerStatus.class, new ServerStatusDeserializer())
                .registerTypeAdapter(DataStats.class, new DataStatsDeserializer())
                .registerTypeAdapter(BoardRank.class, new BoardRankDeserializer())
                .registerTypeAdapter(Ability.class, new AbilityDeserializer())
                .registerTypeAdapter(Champion.class, new ChampionDeserializer())
                .registerTypeAdapter(Skin.class, new SkinDeserializer())
                .create();

        // Assign default values to the session related fields
        this.currentSessionId = config.getSessionId();
        this.createdSessionTimeStamp = System.currentTimeMillis();

        // Check if the session should be refreshed on api startup
        if (config.isRefreshSessionOnStartup()) {
            refreshSession();
        }
    }

    public boolean isSessionValid() {
        return SessionUtils.isValidSession(APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.TEST_SESSION));
    }

    public void refreshSession() {
        // If current sessionId is null, create a new session
        if (currentSessionId == null) {
            createNewSession();
            return;
        }
        // If session is invalid by time, create a new session (reduce requests, if we already know it's invalid)
        long sessionValidUntilTimeStamp = createdSessionTimeStamp + TimeUnit.MINUTES.toMillis(15);
        if (System.currentTimeMillis() >= sessionValidUntilTimeStamp) {
            createNewSession();
            return;
        }
        // If session is invalid by request, create a new session
        if (!isSessionValid()) {
            createNewSession();
        }
    }

    public void createNewSession() {
        // Parse the sessionId by request
        String sessionId = SessionUtils.parseSessionId(APIRequest.create(client, config.getCredentials())
                .method(APIMethod.CREATE_SESSION));
        // Check result
        Validator.notNull(sessionId, "Couldn't create new sessionId.");

        // Assign new sessionId and timeStamp
        this.currentSessionId = sessionId;
        this.createdSessionTimeStamp = System.currentTimeMillis();

        if (!isSessionValid()) {
            throw new RuntimeException("Created new session, but it's invalid.");
        }

        // Check and call new sessionId Consumer
        if (config.getNewSessionIdConsumer() == null) {
            return;
        }
        config.getNewSessionIdConsumer().accept(currentSessionId);
    }

    public boolean isAPIReachable() {
        String result = APIRequest.create(client)
                .method(APIMethod.PING)
                .asString();
        if (Validator.isEmpty(result)) {
            return false;
        }
        result = result.replaceAll("\"", "");
        return result.contains("PaladinsAPI") && result.contains("Ping successful.");
    }

    public DataStats getDataStats() {
        Validator.apiMethod(currentSessionId, "getDataStats");
        JsonObject object = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_DATA_USED)
                .asFirstJsonObject();
        if (object == null) {
            return null;
        }
        return gson.fromJson(object, DataStats.class);
    }

    public List<ServerStatus> getServerStatusList() {
        Validator.apiMethod(currentSessionId, "getServerStatusList");
        List<ServerStatus> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.SERVER_STATUS)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            ServerStatus entity = gson.fromJson(object, ServerStatus.class);
            list.add(entity);
        }
        return list;
    }

    public String getPatchInfo() {
        Validator.apiMethod(currentSessionId, "getPatchInfo");
        JsonObject object = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_PATCH_INFO)
                .asJsonObject();
        if (object == null) {
            return null;
        }
        if (!object.has("version_string") || object.get("version_string").isJsonNull()) {
            return null;
        }
        return object.get("version_string").getAsString();
    }

    public List<Champion> getAllChampions(Language language) {
        Validator.apiMethod(currentSessionId, "getAllChampions");
        List<Champion> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_CHAMPIONS)
                .lang(language)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            Champion entity = gson.fromJson(object, Champion.class);
            entity.setLanguage(language);
            entity.getAllAbilities().forEach(ability -> ability.setLanguage(language));
            list.add(entity);
        }
        return list;
    }

    public List<BoardRank> getChampionLeaderboardConsole(ChampionId championId) {
        return getChampionLeaderboardConsole(championId.getId());
    }

    public List<BoardRank> getChampionLeaderboardConsole(long championId) {
        Validator.apiMethod(currentSessionId, "getChampionLeaderboardConsole");
        List<BoardRank> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_CHAMPION_LEADERBOARD)
                .champion(championId)
                .queue(GameMode.COMPETITIVE_LIVE_CONSOLE.getQueueId())
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            BoardRank entity = gson.fromJson(object, BoardRank.class);
            list.add(entity);
        }
        return list;
    }

    public List<BoardRank> getChampionLeaderboardPC(ChampionId championId) {
        return getChampionLeaderboardPC(championId.getId());
    }

    public List<BoardRank> getChampionLeaderboardPC(long championId) {
        Validator.apiMethod(currentSessionId, "getChampionLeaderboardPC");
        List<BoardRank> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_CHAMPION_LEADERBOARD)
                .champion(championId)
                .queue(GameMode.COMPETITIVE_LIVE_PC.getQueueId())
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            BoardRank entity = gson.fromJson(object, BoardRank.class);
            list.add(entity);
        }
        return list;
    }

    public List<Skin> getChampionSkins(Language language, ChampionId championId) {
        return getChampionSkins(language, championId.getId());
    }

    public List<Skin> getChampionSkins(Language language, long championId) {
        Validator.apiMethod(currentSessionId, "getAllChampionSkins");
        List<Skin> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_CHAMPION_SKINS)
                .champion(championId)
                .lang(language)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            Skin entity = gson.fromJson(object, Skin.class);
            entity.setLanguage(language);
            list.add(entity);
        }
        return list;
    }

    public List<Skin> getAllChampionSkins(Language language) {
        return getChampionSkins(language, -1);
    }

    public List<Item> getItems(Language language) {
        Validator.apiMethod(currentSessionId, "getItems");
        List<Item> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_ITEMS)
                .lang(language)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            if (object.has("item_type") && !object.get("item_type").isJsonNull()
                    // Skip deprecated items.
                    && object.get("item_type").getAsString().startsWith("zDeprecated")) {
                continue;
            }
            Item entity = gson.fromJson(object, Item.class);
            entity.setLanguage(language);
            list.add(entity);
        }
        return list;
    }

    public Player getPlayer(String playerName) {
        Validator.apiMethod(currentSessionId, "getPlayer");
        JsonObject object = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_PLAYER)
                .playerName(playerName)
                .asFirstJsonObject();
        if (object == null) {
            return null;
        }
        return gson.fromJson(object, Player.class);
    }

    public List<PlayerChampion> getPlayerChampions(Player player) {
        return getPlayerChampions(player.getActivePlayerId());
    }

    public List<PlayerChampion> getPlayerChampions(long playerId) {
        Validator.apiMethod(currentSessionId, "getPlayerChampionRanks");
        List<PlayerChampion> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_PLAYER_CHAMPION_RANKS)
                .playerId(playerId)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            PlayerChampion entity = gson.fromJson(object, PlayerChampion.class);
            list.add(entity);
        }
        return list;
    }

    public List<MatchId> getMatchIdByQueue(GameMode gameMode, long timeStamp, Hours hours) {
        return getMatchIdByQueue(gameMode.getQueueId(), timeStamp, hours, null);
    }

    public List<MatchId> getMatchIdByQueue(GameMode gameMode, long timeStamp, Hours hours, Minutes minutes) {
        return getMatchIdByQueue(gameMode.getQueueId(), timeStamp, hours, minutes);
    }

    public List<MatchId> getMatchIdByQueue(int queueId, long timeStamp, Hours hours) {
        return getMatchIdByQueue(queueId, timeStamp, hours, null);
    }

    public List<MatchId> getMatchIdByQueue(int queueId, long date, Hours hours, Minutes minutes) {
        return getMatchIdByQueue(queueId, date, hours.getValue(), minutes == null ? null : minutes.getValue());
    }

    public List<MatchId> getMatchIdByQueue(int queueId, long date, String hours, String minutes) {
        Validator.apiMethod(currentSessionId, "getMatchIdByQueue");
        List<MatchId> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_MATCH_IDS_BY_QUEUE)
                .queue(queueId)
                .timeStamp(date)
                .hours(hours)
                .minutes(minutes)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            MatchId entity = gson.fromJson(object, MatchId.class);
            entity.setGameMode(GameMode.parse(queueId));
            list.add(entity);
        }
        return list;
    }

    public List<MatchPlayer> getMatchDetails(MatchId matchId) {
        return getMatchDetails(matchId.getMatchId());
    }

    public List<MatchPlayer> getMatchDetails(long matchId) {
        Validator.apiMethod(currentSessionId, "getMatchDetails");
        List<MatchPlayer> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_MATCH_DETAILS)
                .matchId(matchId)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            MatchPlayer entity = gson.fromJson(object, MatchPlayer.class);
            list.add(entity);
        }
        return list;
    }

    public List<MatchPlayer> getMatchDetailsBatchLarge(List<Long> matchIdList) {
        int matchIdsPerRequest = 20;
        if (matchIdList.size() > matchIdsPerRequest) {
            List<MatchPlayer> matchPlayerList = new ArrayList<>();
            Iterator<Long> idIterator = matchIdList.iterator();
            List<Long> copyList = new ArrayList<>();
            while (idIterator.hasNext()) {
                if (copyList.size() >= matchIdsPerRequest) {
                    matchPlayerList.addAll(getMatchDetailsBatch(copyList));
                    copyList.clear();
                }
                copyList.add(idIterator.next());
            }
            if (!copyList.isEmpty()) {
                matchPlayerList.addAll(getMatchDetailsBatch(copyList));
            }
            return matchPlayerList;
        }
        return getMatchDetailsBatch(matchIdList);
    }


    public List<MatchPlayer> getMatchDetailsBatch(List<Long> matchIdList) {
        Validator.apiMethod(currentSessionId, "getMatchDetailsBatch");
        List<MatchPlayer> list = new ArrayList<>();
        JsonArray array = APIRequest.create(client, config.getCredentials())
                .session(currentSessionId)
                .method(APIMethod.GET_MATCH_DETAILS_BATCH)
                .matchIdList(matchIdList)
                .asJsonArray();
        if (array == null) {
            return list;
        }
        for (JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            MatchPlayer entity = gson.fromJson(object, MatchPlayer.class);
            list.add(entity);
        }
        return list;
    }
}
