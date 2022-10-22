package eu.koboo.paladins.api.request;

public class URLBuilderPlayerId extends URLBuilderSession {

    public String build(APIRequest request, APIMethod method, long playerId) {
        String prebuildUrl = super.build(request, method);
        return prebuildUrl + "/" + playerId;
    }
}
