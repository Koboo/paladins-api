package eu.koboo.paladins.api.request;

public class URLBuilderLanguage extends URLBuilderSession {

    public String build(APIRequest request, APIMethod method, Language language) {
        String prebuildUrl = super.build(request, method);
        return prebuildUrl + "/" + language.getValue();
    }
}
