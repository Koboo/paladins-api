package eu.koboo.paladins.api.request;

public interface URLBuilder {

    String build(APIRequest request, APIMethod method);
}
