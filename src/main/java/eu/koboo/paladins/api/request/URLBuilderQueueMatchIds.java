package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;
import eu.koboo.paladins.api.utils.DateFormatter;

public class URLBuilderQueueMatchIds extends URLBuilderSession {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String prebuildUrl = super.build(request);
        String hourSegment = request.getHours().getValue();
        if(request.getMinutes() != null) {
            hourSegment += "," + request.getMinutes().getValue();
        }
        return prebuildUrl + "/" + request.getQueue() + "/" + DateFormatter.formatPathDate(request.getTimeStamp()) + "/" + hourSegment;
    }
}
