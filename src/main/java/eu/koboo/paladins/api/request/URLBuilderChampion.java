package eu.koboo.paladins.api.request;

public class URLBuilderChampion extends URLBuilderSession {

    public String build(APIRequest request, APIMethod method, Language language, long championId) {
        String prebuildUrl = super.build(request, method);
        return prebuildUrl + "/" + championId + "/" + language.getValue();
    }
}
