package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.exceptions.URLParameterException;
import eu.koboo.paladins.api.utils.DateFormatter;

public class URLBuilderSession implements URLBuilder {

    @Override
    public String build(APIRequest request) throws URLParameterException {
        String methodUrlFormat = request.getMethod().getName() + "Json";
        String devId = request.getCredentials().devId();
        String currentDate = DateFormatter.formatDate();
        String signature = APIMethod.createSignature(devId, request.getCredentials().authKey(), request.getMethod().getName(), currentDate);
        return APIMethod.URL + "/" + methodUrlFormat + "/" + devId + "/" + signature + "/" + request.getSessionId() + "/" + currentDate;
    }
}
