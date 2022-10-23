package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderPing implements URLBuilder {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        return APIMethod.URL + "/" + request.getMethod().getName() + "Json";
    }
}
