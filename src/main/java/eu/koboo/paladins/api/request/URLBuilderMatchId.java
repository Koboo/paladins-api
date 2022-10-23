package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderMatchId extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        if(request.getMatchId() == -1) {
            throw new URLParameterException(request.getMethod(), "matchId");
        }
        return prebuildUrl + "/" + request.getMatchId();
    }
}
