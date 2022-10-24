package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;
import eu.koboo.paladins.api.utils.DateFormatter;

public class URLBuilderQueueMatchIds extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        String hourSegment = request.getHours();
        if(request.getMinutes() != null) {
            hourSegment += "," + request.getMinutes();
        }
        return prebuildUrl + "/" + request.getQueue() + "/" + DateFormatter.formatPathDate(request.getTimeStamp()) + "/" + hourSegment;
    }
}
