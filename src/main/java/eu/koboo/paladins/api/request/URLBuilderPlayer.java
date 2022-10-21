package eu.koboo.paladins.api.request;

public class URLBuilderPlayer extends URLBuilderSession {

    public String build(APIRequest request, APIMethod method, String player) {
        String prebuildUrl = super.build(request, method);
        return prebuildUrl + "/" + player;
    }
}
