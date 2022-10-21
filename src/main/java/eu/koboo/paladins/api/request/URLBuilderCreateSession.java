package eu.koboo.paladins.api.request;

import eu.koboo.paladins.api.utils.DateFormatter;

public class URLBuilderCreateSession implements URLBuilder {

    @Override
    public String build(APIRequest request, APIMethod method) {
        String methodUrlFormat = method.getName() + "Json";
        String devId = request.getCredentials().devId();
        String currentDate = DateFormatter.formatDate();
        String signature = APIMethod.createSignature(devId, request.getCredentials().authKey(), method.getName(), currentDate);
        return APIMethod.URL + "/" + methodUrlFormat + "/" + devId + "/" + signature + "/" + currentDate;
    }
}
