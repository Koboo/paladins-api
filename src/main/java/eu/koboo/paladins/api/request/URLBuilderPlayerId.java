package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderPlayerId extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        return prebuildUrl + "/" + request.getPlayerId();
    }
}
