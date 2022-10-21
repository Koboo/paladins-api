package eu.koboo.paladins.api.request;

public class URLBuilderPing implements URLBuilder {

    @Override
    public String build(APIRequest request, APIMethod method) {
        return APIMethod.URL + "/" + method.getName() + "Json";
    }
}
