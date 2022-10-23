# Paladins Java API

Simple and up-to-date java wrapper for [HiRez' Paladins REST-API](https://www.paladins.com/).

**Note:** If any changes from the api are not already implemented into this wrapper, please let me know through
an [issue](/issues), [mail](mailto://admin@koboo.eu) or [pull-request](/pulls).

(updated fork of HeyZeer0's [Paladins-Java-API](https://github.com/HeyZeer0/Paladins-Java-API))

# Overview

- [Requirements](#requirements)
- [Dependency](#dependency)
- [Example](#get-started)
- [References](#references--sources)
- [License](/LICENSE)

## Requirements

- Java 17
- Gradle or Maven

## Dependency

````groovy
respositories {
    maven {
        name 'koboo-reposilite'
        url 'https://reposilite.koboo.eu'
    }
}

dependencies {
    implementation 'eu.koboo.paladins:paladins-api:1.0.0'
}
````

## Get Started

````java
public class Example {
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

        String devId = config.get("devId").asString();
        Validator.notNull(devId, "DevId is null");

        String authKey = config.get("authKey").asString();
        Validator.notNull(devId, "AuthKey is null");

        System.out.println("=====> Creating API wrapper..");
        PaladinsAPI api = new PaladinsAPI(APIConfig.of(devId, authKey)
                .requestTimeout(10)
                .sessionId(config.get("sessionId").asString())
                .refreshSessionOnStartup(true)
                .onNewSessionId(newSessionId -> {
                    config.set("sessionId", newSessionId);
                    config.save(configFile);
                }));

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
                System.out.println("Server: " + serverStatus.getPlatform().name() + " | " + serverStatus.getStatusText());
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
            List<BoardRank> boardRankList = api.getChampionLeaderboard(ioChampionId);
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

        if(which[8]) {
            System.out.println("=====> Getting Player..");
            Player player = api.getPlayer("Muffeltier");
            System.out.println("Player: " + player.getPlayerId() + " | " + player.getPaladinsName() + " | " + player.getPlatformName());
            List<PlayerChampion> list = api.getPlayerChampions(player.getPlayerId());
            System.out.println("Played Champions: " + list.size());
        }

        if(which[9]) {
            List<MatchId> matchIdList = api.getMatchIdByQueue(GameMode.SIEGE_CASUAL.getQueueId(), System.currentTimeMillis(), Hours.TEN, null);
            System.out.println("MatchIds: " + matchIdList.size());
            long first = matchIdList.get(0).getMatchId();
            System.out.println("First Match: " + first);
            long second = matchIdList.get(1).getMatchId();
            System.out.println("Second Match: " + second);
            long third = matchIdList.get(2).getMatchId();
            System.out.println("Third Match: " + third);
            List<MatchPlayer> matchDetails = api.getMatchDetails(Arrays.asList(first, second, third));
            System.out.println("Players in Match: " + matchDetails.size());
        }
    }
}
````

## References & Sources
- [Official Documentation](https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/edit)
- [Unoffical Documentation](https://github.com/luissilva1044894/hirez-api-docs)
- [Java 9 HttpClient](https://www.baeldung.com/java-9-http-client)
- [Paladins Fandom Wiki](https://paladins.fandom.com/wiki)
- [Paladins Wrapper](https://github.com/Cristian-Sknz/Paladins-Wrapper)
