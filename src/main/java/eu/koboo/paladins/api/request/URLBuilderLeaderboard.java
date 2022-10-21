package eu.koboo.paladins.api.request;

public class URLBuilderLeaderboard extends URLBuilderSession {

    public String build(APIRequest request, APIMethod method, long championId, long queue) {
        String prebuildUrl = super.build(request, method);
        return prebuildUrl + "/" + championId + "/" + queue;
    }
}
