package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderPlayerName extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        return prebuildUrl + "/" + request.getPlayerName();
    }
}
