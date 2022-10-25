package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;

public class URLBuilderMatchIdList extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        if (request.getMatchIdList().isEmpty()) {
            throw new URLParameterException(request.getMethod(), "matchIdList");
        }
        StringBuilder matchIdSegment = new StringBuilder();
        for (Long matchId : request.getMatchIdList()) {
            matchIdSegment.append(",").append(matchId);
        }
        return prebuildUrl + "/" + matchIdSegment.toString().replaceFirst(",", "");
    }
}
