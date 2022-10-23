package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderLeaderboard extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        if(request.getQueue() == -1) {
            throw new URLParameterException(request.getMethod(), "queue");
        }
        return prebuildUrl + "/" + request.getChampionId() + "/" + request.getQueue();
    }
}
