package eu.koboo.paladins.api.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@RequiredArgsConstructor
public enum APIMethod {

    /* Connectivity, Development and System Status */
    PING("ping", new URLBuilderPing()),
    CREATE_SESSION("createsession", new URLBuilderCreateSession()),
    TEST_SESSION("testsession", new URLBuilderSession()),
    GET_DATA_USED("getdataused", new URLBuilderSession()),
    SERVER_STATUS("gethirezserverstatus", new URLBuilderSession()),
    GET_PATCH_INFO("getpatchinfo", new URLBuilderSession()),

    /* Champions and Items */
    GET_CHAMPIONS("getchampions", new URLBuilderLanguage()),
    GET_CHAMPION_LEADERBOARD("getchampionleaderboard", new URLBuilderLeaderboard()),
    GET_CHAMPION_SKINS("getchampionskins", new URLBuilderChampion()),

    // Unsupported and deprecated by this API. Use APIMethod.GET_ITEMS instead.,
    GET_CHAMPION_CARDS("getchampioncards", new URLBuilderChampion()),

    ;

    static final String URL = "https://api.paladins.com/paladinsapi.svc";

    String name;
    URLBuilder urlBuilder;

    public String url(APIRequest request, Language language, long championId, long queue) {

        if(urlBuilder instanceof URLBuilderLanguage languageBuilder) {
            return languageBuilder.build(request, this, language);
        }

        if(urlBuilder instanceof URLBuilderChampion championBuilder) {
            return championBuilder.build(request, this, language, championId);
        }

        if(urlBuilder instanceof URLBuilderLeaderboard leaderboardBuilder) {
            return leaderboardBuilder.build(request, this, championId, queue);
        }

        return urlBuilder.build(request, this);
    }

    public static String createSignature(String devId, String authKey, String methodCall, String currentDate) {
        StringBuilder stringToHash = new StringBuilder();
        stringToHash.append(devId).append(methodCall).append(authKey).append(currentDate);
        try {
            StringBuilder hashBuilder = new StringBuilder();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(stringToHash.toString().getBytes());
            byte[] bytes = md.digest();

            for (byte b : bytes) {
                String hx = Integer.toHexString(0xff & b);
                if (hx.length() == 1) {
                    hashBuilder.append("0");
                }
                hashBuilder.append(hx);
            }
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
