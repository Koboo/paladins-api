package eu.koboo.paladins.api.exceptions;

import eu.koboo.paladins.api.request.APIMethod;
import eu.koboo.paladins.api.request.APIRequest;

public class URLParameterException extends Exception {

    public URLParameterException(APIMethod method, String parameterName) {
        super("Missing parameter \"" + parameterName + "\" for api method \"" + method.getName() + "\".");
    }
}
